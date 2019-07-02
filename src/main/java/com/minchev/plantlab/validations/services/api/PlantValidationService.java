package com.minchev.plantlab.validations.services.api;

import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;

public interface PlantValidationService {
    boolean isValidPlantServiceModel(PlantServiceModel plantServiceModel);

    boolean isValidPlanterviceEditModel(PlantServiceEditModel plantServiceEditModel);
}
