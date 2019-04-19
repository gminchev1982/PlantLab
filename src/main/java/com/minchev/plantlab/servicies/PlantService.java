package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.models.service.PlantServiceModel;

import java.util.List;

public interface PlantService {
     PlantServiceModel findByBarcode(String barcode);
     PlantServiceModel save(PlantServiceModel plantServiceModel);
     List<PlantServiceModel> findAllPlants();

}
