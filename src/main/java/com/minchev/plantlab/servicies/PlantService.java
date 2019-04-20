package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;

import java.util.List;

public interface PlantService {
     PlantServiceModel findByBarcode(String barcode);
     PlantServiceModel save(PlantServiceModel plantServiceModel);
     PlantServiceEditModel edit(PlantServiceEditModel plantServiceEditModel);
     List<PlantServiceModel> findAllPlants(Integer page, String sort,  String search);
     PlantServiceModel findPlantById(String id);
     List<Integer> getPagingNumber();
}
