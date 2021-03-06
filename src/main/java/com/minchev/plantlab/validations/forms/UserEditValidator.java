package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.database.repositories.UserRepository;
import com.minchev.plantlab.models.forms.UserRegisterForm;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserEditValidator implements org.springframework.validation.Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserEditValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterForm UserRegisterForm = (UserRegisterForm) o;

        if (UserRegisterForm.getRole() == null || UserRegisterForm.getRole() == "" || UserRegisterForm.getRole().isEmpty()) {
            errors.rejectValue(
                    "role",
                    ValidationConstants.ROLES_EMPTY,
                    ValidationConstants.ROLES_EMPTY
            );
        }

        if (!UserRegisterForm.getPassword().isEmpty() && !UserRegisterForm.getPassword().equals(UserRegisterForm.getConfirmPassword())) {
            errors.rejectValue(
                    "confirmPassword",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }

    }
}
