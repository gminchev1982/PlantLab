package com.minchev.plantlab.validations.services.api;

import com.minchev.plantlab.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel userServiceModel);
}
