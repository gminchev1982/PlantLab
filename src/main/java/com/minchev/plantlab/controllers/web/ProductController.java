package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.servicies.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController extends  BaseController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService  = productService;
    }

    @GetMapping("/products")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView productPage(ModelAndView modelAndView,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "sort", defaultValue = "createdAt,desc") String sort) {

        modelAndView.addObject("products", this.productService.findAllProduct(page, sort));
        modelAndView.addObject("pageNumbers", this.productService.getPagingNumber());
        modelAndView.addObject("page", page);
        modelAndView.addObject("sort", sort);


        return view("products/list", modelAndView);
    }

}
