package com.minchev.plantlab.validations.services;

import com.minchev.plantlab.models.service.ProductServiceModel;

public interface ProductValidationService {

    boolean isValid(ProductServiceModel product);

}
