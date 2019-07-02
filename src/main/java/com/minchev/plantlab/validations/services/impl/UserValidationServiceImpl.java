package com.minchev.plantlab.validations.services.impl;

import com.minchev.plantlab.models.service.UserServiceModel;
import com.minchev.plantlab.validations.services.api.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel userServiceModel) {
        return userServiceModel != null;
    }
}
