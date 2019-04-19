package com.minchev.plantlab.controllers.web;



import com.minchev.plantlab.controllers.BaseController;
import com.minchev.plantlab.models.forms.UserRegisterForm;
import com.minchev.plantlab.models.service.UserServiceModel;
import com.minchev.plantlab.models.view.UserAllViewModel;
import com.minchev.plantlab.servicies.RoleService;
import com.minchev.plantlab.servicies.UserService;
import com.minchev.plantlab.validations.UserEditValidator;
import com.minchev.plantlab.validations.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;
    private final RoleService roleService;
    private final UserEditValidator userEditValidator;

    @Autowired
    public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator, UserEditValidator userEditValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
        this.userEditValidator = userEditValidator;
        this.roleService = roleService;
        
      //  this.userEditValidator = userEditValidator;
    }
    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    //@PageTitle("Register")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model) {
        modelAndView.addObject("model", model);
        modelAndView.addObject("roles", roleService.findAllRoles());
        return view("user/register", modelAndView);
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model, BindingResult bindingResult) {
        this.userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            modelAndView.addObject("roles", roleService.findAllRoles());
            modelAndView.addObject("path", "/register");
            return view("user/register", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        return redirect("users/login");
    }


    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    //PageTitle("Login")
    public ModelAndView login() {
        return view("user/login");
    }


    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
   // @PageTitle("All Users")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u ->  this.modelMapper.map(u, UserAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);

        return view("user/list", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    //@PageTitle("Edit Category")
        public ModelAndView editUser(@PathVariable String id, ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model) {
        model = this.modelMapper.map(this.userService.findUserById(id), UserRegisterForm.class);
        model.setPassword(null);
        modelAndView.addObject("userId", id);
        modelAndView.addObject("model", model);
        modelAndView.addObject("roles", roleService.findAllRoles());
        return view("user/edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editUser(@PathVariable String id, ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model, BindingResult bindingResult, Principal principal) {
        this.userEditValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            modelAndView.addObject("roles", roleService.findAllRoles());
            return view("/user/edit", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        userServiceModel.setUsername(principal.getName());
        this.userService.editUserProfile(userServiceModel);

        return redirect("/users/all");
    }
}
