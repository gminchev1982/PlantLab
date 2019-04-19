package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.RoleEntity;
import com.minchev.plantlab.databases.repositories.RoleRepository;
import com.minchev.plantlab.models.service.RoleServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new RoleEntity("role_user"));
            this.roleRepository.saveAndFlush(new RoleEntity("role_moderator"));
            this.roleRepository.saveAndFlush(new RoleEntity("role_admin"));
            this.roleRepository.saveAndFlush(new RoleEntity("role_root"));
        }
    }

//    @Override
//    public void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers) {
//        if (numberOfUsers == 0) {
//            userServiceModel
//                    .setAuthorities(this.roleRepository
//                            .findAll()
//                            .stream()
//                            .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
//                            .collect(Collectors.toSet()));
//        }
//    }


    @Override
    public Set<RoleServiceModel> findAllRoles() {
        this.seedRolesInDb();


        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }

    public RoleServiceModel findById (String id) {
        Optional<RoleEntity> role = this.roleRepository.findById(id);

        if (role.isPresent()) {
            return this.modelMapper.map(role.get(), RoleServiceModel.class);
        }else return null;

    }

}
