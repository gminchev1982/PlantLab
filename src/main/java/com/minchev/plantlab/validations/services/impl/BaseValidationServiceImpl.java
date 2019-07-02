package com.minchev.plantlab.validations.services.impl;

import com.minchev.plantlab.validations.services.api.BaseValidationService;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseValidationServiceImpl<T> implements BaseValidationService<T> {
    @Override
    public boolean isValid(T validate) {
        return validate != null;
    }
}
