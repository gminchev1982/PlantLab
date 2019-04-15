package com.minchev.plantlab.controllers;



import com.minchev.plantlab.models.forms.UserRegisterForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    //@PageTitle("Register")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model) {
        modelAndView.addObject("model", model);

        return view("user/register", modelAndView);
    }

}
