package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.view.PlantLabViewModel;
import com.minchev.plantlab.models.view.PlantListViewModel;

import java.util.List;

public interface PlantService {
     PlantServiceModel findByBarcode(String barcode);
     PlantServiceModel save(PlantServiceModel plantServiceModel);
     PlantServiceEditModel edit(PlantServiceEditModel plantServiceEditModel);
     List<PlantListViewModel> findAllPlants(Integer page, String sort, String search);
     List<PlantLabViewModel> findAllPlantsActive();
     PlantServiceModel findPlantById(String id);
     List<Integer> getPagingNumber();
}
