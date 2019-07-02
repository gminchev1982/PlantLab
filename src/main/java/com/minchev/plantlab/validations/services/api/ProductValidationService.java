package com.minchev.plantlab.validations.services.api;

import com.minchev.plantlab.models.service.ProductServiceEditModel;
import com.minchev.plantlab.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValidProductServiceModel(ProductServiceModel product);

    boolean isValidProductServiceEditModel(ProductServiceEditModel product);
}
