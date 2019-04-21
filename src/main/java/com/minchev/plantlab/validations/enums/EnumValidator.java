package com.minchev.plantlab.validations.enums;

import com.minchev.plantlab.validations.anotations.ValidateEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidateEnum, String> {
    private Set<String> allowedValues;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void initialize(ValidateEnum targetEnum) {
    Class<? extends Enum> enumSelected = targetEnum.targetClassType();
         allowedValues = (Set<String>) EnumSet.allOf(enumSelected).stream().map(e -> ((Enum<? extends Enum<?>>) e).name())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

}