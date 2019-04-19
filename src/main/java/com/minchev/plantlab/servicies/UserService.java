package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserName(String username);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findUserById(String id);

    void setUserRole(UserServiceModel userServiceModel, String role);
}
