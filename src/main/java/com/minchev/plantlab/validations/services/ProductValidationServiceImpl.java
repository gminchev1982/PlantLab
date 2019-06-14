package com.minchev.plantlab.validations.services;

import com.minchev.plantlab.models.service.ProductServiceModel;
import org.springframework.stereotype.Component;

@Component
    public class ProductValidationServiceImpl  implements ProductValidationService{
    @Override
    public boolean isValid(ProductServiceModel product) {
        return  product != null;
    }
}
