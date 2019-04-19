package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

//    void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers);

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
    RoleServiceModel findById(String id);
}
