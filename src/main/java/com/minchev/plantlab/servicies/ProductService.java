package com.minchev.plantlab.servicies;

import com.minchev.plantlab.models.forms.ProductEditForm;
import com.minchev.plantlab.models.service.ProductServiceEditModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;

import java.util.List;

public interface ProductService {
    ProductServiceModel save(ProductServiceModel productServiceModel);
    boolean edit(ProductServiceEditModel productServiceEditModel);
    List<ProductServiceModel> findAllProduct(Integer page, String sort);
    List<ProductLabViewModel> findAllProductActive();
    List<Integer> getPagingNumber();
    ProductServiceModel findById(String id);
}
