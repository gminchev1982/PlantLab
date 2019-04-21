package com.minchev.plantlab.databases.repositories;

import com.minchev.plantlab.databases.entities.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LabRepository extends JpaRepository<LabEntity,String> {
}
