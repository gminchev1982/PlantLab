package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.interceptors.PageTitle;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.servicies.PlantService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PageTitle("Plants")
    public ModelAndView homePlant(ModelAndView modelAndView,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "sort", defaultValue = "createdAt,desc") String sort,
                                  @RequestParam(value = "search", defaultValue = "") String search){


        modelAndView.addObject("plantAll", this.plantService.findAllPlants(page, sort, search));
        modelAndView.addObject("pageNumbers", this.plantService.getPagingNumber());
        modelAndView.addObject("page", page);
        modelAndView.addObject("sort", sort);

        return view("plants/list", modelAndView);
    }
}
