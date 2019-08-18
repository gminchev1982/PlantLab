package com.minchev.plantlab.servicies.api;

import com.minchev.plantlab.errors.PlantNotFoundException;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.view.PlantLabViewModel;
import com.minchev.plantlab.models.view.PlantListViewModel;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PlantService  extends PaginationService {
    PlantServiceModel findByBarcode(String barcode);

    Boolean save(PlantServiceModel plantServiceModel);

    List<PlantListViewModel> findAllPlants(PageRequest pageable);

    List<PlantLabViewModel> findAllPlantsActive();

    PlantServiceModel findPlantById(String id) throws PlantNotFoundException;



}
