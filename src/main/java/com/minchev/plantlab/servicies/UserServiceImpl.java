package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.UserEntity;
import com.minchev.plantlab.databases.repositories.UserRepository;
import com.minchev.plantlab.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDb();
        userServiceModel.setAuthorities(new LinkedHashSet<>());
        userServiceModel.getAuthorities().add(this.roleService.findById(userServiceModel.getRole()));


        //setUserRole(userServiceModel, userServiceModel.getRole());

        UserEntity user = this.modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
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
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel) {
            UserEntity user = this.userRepository.findByUsername(userServiceModel.getUsername())
                    .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
            user.setName(userServiceModel.getName());
            user.setRole(userServiceModel.getRole());
        user.setPassword(userServiceModel.getPassword() != null || !userServiceModel.getPassword().isEmpty()  ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        //user.setUsername(userServiceModel.getEmail());

        //setAuthotiries
        userServiceModel.setAuthorities(new LinkedHashSet<>());
        userServiceModel.getAuthorities().add(this.roleService.findById(userServiceModel.getRole()));

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
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
    public void setUserRole(UserServiceModel userServiceModel, String role) {

       userServiceModel.getAuthorities().clear();

        switch (role) {
            case "ROLE_USER":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "ROLE_MANAGER":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
            case "ROLE_ADMIN":
            case "ROLE_ROOT":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
                break;
        }
//this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, UserEntity.class));
        //return  userServiceModel;
    }
}
