package com.minchev.plantlab.databases.repositories;

import com.minchev.plantlab.databases.entities.PlantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<PlantEntity,String> {
    Optional<PlantEntity> findByBarcode(String barcode);
}

