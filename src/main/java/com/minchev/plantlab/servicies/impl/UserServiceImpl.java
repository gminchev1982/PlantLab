package com.minchev.plantlab.servicies.impl;

import com.minchev.plantlab.database.entities.UserEntity;
import com.minchev.plantlab.database.repositories.UserRepository;
import com.minchev.plantlab.models.service.UserServiceModel;
import com.minchev.plantlab.servicies.api.CloudinaryService;
import com.minchev.plantlab.servicies.api.RoleService;
import com.minchev.plantlab.servicies.api.UserService;
import com.minchev.plantlab.validations.services.api.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CloudinaryService cloudinaryService;
    private final UserValidationService userValidaionService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserValidationService userValidationService,
                           CloudinaryService cloudinaryService,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userValidaionService = userValidationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean save(UserServiceModel userServiceModel) throws IOException {

        this.roleService.seedRolesInDb();

        if (userValidaionService.isValid(userServiceModel)) {
            userBcrypePasword(userServiceModel);
            userAuthorities(userServiceModel);
            userImage(userServiceModel);
            UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
            this.userRepository.saveAndFlush(user);

            return true;
        } else return false;


        // return this.modelMapper.map(this.userRepository.saveAndFlush(user) , UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public Boolean edit(UserServiceModel userServiceModel) throws IOException {

        if (userValidaionService.isValid(userServiceModel)) {
            UserEntity user = this.userRepository.findByUsername(userServiceModel.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

            userBcrypePasword(userServiceModel);

            if (userServiceModel.getPassword().isEmpty() && userServiceModel.getPassword() == null) {
                userServiceModel.setPassword(user.getPassword());
            }

            userAuthorities(userServiceModel);
            userImage(userServiceModel);
            userServiceModel.setId(user.getId());

            this.userRepository.save(this.modelMapper.map(userServiceModel, UserEntity.class));
            return true;
        } else return false;
    }


    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return this.userRepository.findById(id)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }


    @Override
    public UserServiceModel setUserRole(UserServiceModel userServiceModel) {

        userServiceModel.getAuthorities().clear();

        switch (userServiceModel.getRole()) {
            case "role_user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("role_user"));
                break;
            case "role_manager":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("role_manager"));
                break;
            case "role_admin":
            case "role_root":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("role_manager"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("role_admin"));
                break;
        }
        //this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, UserEntity.class));
        return userServiceModel;
    }


    void userBcrypePasword(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
    }


    void userAuthorities(UserServiceModel userServiceModel) {
        userServiceModel.setAuthorities(new LinkedHashSet<>());
        userServiceModel.getAuthorities().add(this.roleService.findById(userServiceModel.getRole()));
    }

    void userImage(UserServiceModel userServiceModel) throws IOException {
        if (!userServiceModel.getImages().isEmpty() || userServiceModel.getImages() != null) {
            userServiceModel.setImage(this.cloudinaryService.uploadImage(userServiceModel.getImages()));
        } else userServiceModel.setImage("");


    }

}
