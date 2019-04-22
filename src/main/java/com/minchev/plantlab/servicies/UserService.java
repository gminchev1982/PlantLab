package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel) throws IOException;

    UserServiceModel findUserByUserName(String username);

    Boolean editUserProfile(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findUserById(String id);

    UserServiceModel setUserRole(UserServiceModel userServiceModel);
}
