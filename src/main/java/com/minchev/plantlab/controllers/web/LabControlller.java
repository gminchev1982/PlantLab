package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.databases.enums.Health;
import com.minchev.plantlab.servicies.LabService;
import com.minchev.plantlab.servicies.PlantService;
import com.minchev.plantlab.servicies.ProductService;
import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LabControlller extends  BaseController{


    private final PlantService plantService;
    private final ProductService productService;
    private final LabService labService;

    public LabControlller(PlantService plantService, ProductService productService, LabService labService) {
        this.plantService = plantService;
        this.productService = productService;
        this.labService = labService;
    }

    @GetMapping("/labs")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView homeLabs(ModelAndView modelAndView,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "sort", defaultValue = "createdAt,desc") String sort){
        modelAndView.addObject("plants", this.plantService.findAllPlantsActive());
        modelAndView.addObject("products", this.productService.findAllProductActive());
        modelAndView.addObject("status", Health.values());

        modelAndView.addObject("labs", this.labService.findAllLabs(page, sort));

        return view("labs/list", modelAndView);
    }
}