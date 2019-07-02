package com.minchev.plantlab.servicies.api;

import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;

import java.util.List;

public interface ProductService {
    boolean save(ProductServiceModel productServiceModel);

    //boolean edit(ProductServiceEditModel productServiceEditModel) throws ProductNotFoundException;

    List<ProductServiceModel> findAllProduct(Integer page, String sort);

    List<ProductLabViewModel> findAllProductActive();

    List<Integer> getPagingNumber();

    ProductServiceModel findById(String id) throws ProductNotFoundException;
}
