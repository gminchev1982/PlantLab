package com.minchev.plantlab.validations.services.api;

import com.minchev.plantlab.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValidProductServiceModel(ProductServiceModel product);
}
