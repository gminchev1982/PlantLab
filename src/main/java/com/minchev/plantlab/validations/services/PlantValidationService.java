package com.minchev.plantlab.validations.services;

import com.minchev.plantlab.models.service.PlantServiceModel;

public interface PlantValidationService {
    boolean isValid(PlantServiceModel product);
}
