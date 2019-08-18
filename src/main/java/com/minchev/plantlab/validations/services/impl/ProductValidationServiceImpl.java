package com.minchev.plantlab.validations.services.impl;

import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.validations.services.api.ProductValidationService;
import org.springframework.stereotype.Component;

@Component
public class ProductValidationServiceImpl implements ProductValidationService {


    @Override
    public boolean isValidProductServiceModel(ProductServiceModel product) {
        return product != null;
    }

}
