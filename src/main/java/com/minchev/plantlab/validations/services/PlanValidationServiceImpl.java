package com.minchev.plantlab.validations.services;

import com.minchev.plantlab.models.service.PlantServiceModel;
import org.springframework.stereotype.Component;

@Component
public class PlanValidationServiceImpl implements PlantValidationService {
    @Override
    public boolean isValid(PlantServiceModel plant) {
        return  plant != null;
    }
}
