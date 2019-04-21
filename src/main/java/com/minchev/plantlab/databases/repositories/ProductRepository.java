package com.minchev.plantlab.databases.repositories;

import com.minchev.plantlab.databases.entities.PlantEntity;
import com.minchev.plantlab.databases.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findById(String id);
    @Override
    Page<ProductEntity> findAll(Pageable pageable);
}
