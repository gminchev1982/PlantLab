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
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel)  throws IOException {
        this.roleService.seedRolesInDb();
        userServiceModel.setAuthorities(new LinkedHashSet<>());
        userServiceModel.getAuthorities().add(this.roleService.findById(userServiceModel.getRole()));
        userServiceModel.setImage(this.cloudinaryService.uploadImage(userServiceModel.getImages()));
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
    public Boolean editUserProfile(UserServiceModel userServiceModel) {
        UserEntity user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
        /*user.setName(userServiceModel.getName());
        user.setRole(userServiceModel.getRole());
        user.setPassword(userServiceModel.getPassword() != null || !userServiceModel.getPassword().isEmpty()  ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());*/
        //user.setUsername(userServiceModel.getEmail());
        userServiceModel.setId(user.getId());
        if (userServiceModel.getPassword()!="" && userServiceModel.getPassword() != null || !userServiceModel.getPassword().isEmpty()){
            userServiceModel.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        }else userServiceModel.setPassword(user.getPassword());

        userServiceModel.setAuthorities(new LinkedHashSet<>());
        userServiceModel.getAuthorities().add(this.roleService.findById(userServiceModel.getRole()));


           try {
               this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, UserEntity.class));
               return true;
           }catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        //setAuthotiries


        //return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

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
        return  userServiceModel;
    }
}
