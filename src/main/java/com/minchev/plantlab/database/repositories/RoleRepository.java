package com.minchev.plantlab.database.repositories;

import com.minchev.plantlab.database.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findByAuthority(String authority);

    Optional<RoleEntity> findById(String authority);

}
