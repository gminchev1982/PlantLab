package com.minchev.plantlab.servicies.api;

import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService  extends PaginationService{
    boolean save(ProductServiceModel productServiceModel);
    List<ProductServiceModel> findAllProduct(PageRequest pageable);
    List<ProductLabViewModel> findAllProductActive();
    ProductServiceModel findById(String id) throws ProductNotFoundException;
}
