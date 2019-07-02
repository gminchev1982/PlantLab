package com.minchev.plantlab.servicies.api;

import com.minchev.plantlab.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {

    Boolean save(UserServiceModel userServiceModel) throws IOException;

    UserServiceModel findUserByUserName(String username);

    Boolean edit(UserServiceModel userServiceModel) throws IOException;

    List<UserServiceModel> findAllUsers();

    UserServiceModel findUserById(String id);

    UserServiceModel setUserRole(UserServiceModel userServiceModel);
}
