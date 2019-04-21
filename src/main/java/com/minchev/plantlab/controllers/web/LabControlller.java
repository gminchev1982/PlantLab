package com.minchev.plantlab.controllers.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class LabControlller extends  BaseController{

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView homePlant(ModelAndView modelAndView,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "sort", defaultValue = "createdAt,desc") String sort,
                                  @RequestParam(value = "search", defaultValue = "") String search){


        return view("labs/list");
    }
}
