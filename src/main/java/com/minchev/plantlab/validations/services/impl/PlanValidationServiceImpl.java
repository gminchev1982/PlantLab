package com.minchev.plantlab.validations.services.impl;

import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.validations.services.api.PlantValidationService;
import org.springframework.stereotype.Component;

@Component
public class PlanValidationServiceImpl implements PlantValidationService {


    @Override
    public boolean isValidPlantServiceModel(PlantServiceModel plantServiceModel) {
        return plantServiceModel != null;
    }
}
