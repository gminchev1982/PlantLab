package com.minchev.plantlab.servicies.api;

import com.minchev.plantlab.errors.PlantNotFoundException;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.view.PlantLabViewModel;
import com.minchev.plantlab.models.view.PlantListViewModel;

import java.util.List;

public interface PlantService {
    PlantServiceModel findByBarcode(String barcode);

    Boolean save(PlantServiceModel plantServiceModel);

    List<PlantListViewModel> findAllPlants(Integer page, String sort, String search);

    List<PlantLabViewModel> findAllPlantsActive();

    PlantServiceModel findPlantById(String id) throws PlantNotFoundException;

    List<Integer> getPagingNumber();
}
