package com.minchev.plantlab.validations;

import com.minchev.plantlab.databases.repositories.UserRepository;
import com.minchev.plantlab.models.forms.UserRegisterForm;
import com.minchev.plantlab.validations.anotations.Validator;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator  implements org.springframework.validation.Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterForm UserRegisterForm = (UserRegisterForm) o;

        if (UserRegisterForm.getName()==null || UserRegisterForm.getName()=="" || UserRegisterForm.getName().isEmpty()) {

            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }


        if (this.userRepository.findByUsername(UserRegisterForm.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, UserRegisterForm.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, UserRegisterForm.getUsername())
            );
        }

        if (UserRegisterForm.getUsername().length() < 3 || UserRegisterForm.getUsername().length() > 50) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }

        if (!UserRegisterForm.getPassword().equals(UserRegisterForm.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }

        if (UserRegisterForm.getRole()==null || UserRegisterForm.getRole()=="" || UserRegisterForm.getRole().isEmpty()) {

            errors.rejectValue(
                    "role",
                    ValidationConstants.ROLES_EMPTY,
                    ValidationConstants.ROLES_EMPTY
            );
        }


        /*if (this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail())
            );
        }*/
    }
}
