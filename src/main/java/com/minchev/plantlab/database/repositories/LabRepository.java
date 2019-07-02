package com.minchev.plantlab.database.repositories;

import com.minchev.plantlab.database.entities.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LabRepository extends JpaRepository<LabEntity, String> {
}
