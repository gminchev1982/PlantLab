package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.controllers.BaseController;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.servicies.PlantService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/plants")
public class PlantController extends BaseController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView homePlant(ModelAndView modelAndView) {

        List<PlantServiceModel> plantsAll = this.plantService.findAllPlants();
        modelAndView.addObject("plantAll", plantsAll);
        return view("plants/list", modelAndView);
    }
}
