package com.minchev.plantlab.controllers.web;


import com.minchev.plantlab.models.forms.UserRegisterForm;
import com.minchev.plantlab.models.service.UserServiceModel;
import com.minchev.plantlab.models.view.UserAllViewModel;
import com.minchev.plantlab.servicies.api.RoleService;
import com.minchev.plantlab.servicies.api.UserService;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import com.minchev.plantlab.validations.forms.UserEditValidator;
import com.minchev.plantlab.validations.forms.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;
    private final RoleService roleService;
    private final UserEditValidator userEditValidator;
    Boolean result;

    @Autowired
    public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper,
                          UserRegisterValidator userRegisterValidator,
                          UserEditValidator userEditValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
        this.userEditValidator = userEditValidator;
        this.roleService = roleService;

        //  this.userEditValidator = userEditValidator;
    }

    @GetMapping("/register")
   // @PreAuthorize("hasRole('ROLE_ROOT')")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model) {
        modelAndView.addObject("model", model);
        modelAndView.addObject("roles", roleService.findAllRoles());
        return view("user/register", modelAndView);
    }


    @PostMapping("/register")
   // @PreAuthorize("hasRole('ROLE_ROOT')")
     @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model, BindingResult bindingResult) throws IOException {
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
        Boolean result = this.userService.save(userServiceModel);
        if (!result) {
            return view("user/register", modelAndView);
        }


        return redirect("/users/all");
    }


    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    //PageTitle("Login")
    public ModelAndView login() {
        return view("user/login");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> this.modelMapper.map(u, UserAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);

        return view("user/list", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
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
    @PreAuthorize("hasRole('ROLE_ROOT')")
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
        try {
            this.userService.edit(userServiceModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return redirect("/users/all");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model, Principal principal) {

        model = this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserRegisterForm.class);
        model.setPassword(null);
        modelAndView.addObject("model", model);
        modelAndView.addObject("roles", roleService.findAllRoles());
        return view("user/profile", modelAndView);
    }


    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterForm model, Principal principal, BindingResult bindingResult) {
        this.userEditValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            modelAndView.addObject("roles", roleService.findAllRoles());
            return view("/user/profile", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        userServiceModel.setUsername(principal.getName());

        try {
            result = this.userService.edit(userServiceModel);
            this.setMassage(modelAndView, result);
            return view("user/profile", modelAndView);
        } catch (IOException e) {

            this.setMassage(modelAndView, false);
            return view("user/profile", modelAndView);


        }
    }


    public void setMassage(ModelAndView modelAndView, boolean result) {
        if (result == true) {
            modelAndView.addObject("message", ValidationConstants.USERNAME_EDIT);
            modelAndView.addObject("flag", true);
        } else {
            modelAndView.addObject("message", ValidationConstants.USERNAME_EDIT_WRONG);
            modelAndView.addObject("flag", false);
        }
    }
}
