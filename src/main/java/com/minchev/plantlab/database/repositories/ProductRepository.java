package com.minchev.plantlab.database.repositories;

import com.minchev.plantlab.database.entities.PlantEntity;
import com.minchev.plantlab.database.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);

    Optional<ProductEntity> findById(String id);
    Optional<ProductEntity> findByActive(Boolean active);
    @Override
    Page<ProductEntity> findAll(Pageable pageable);
}
