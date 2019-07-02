package com.minchev.plantlab.validations.services.api;

public interface BaseValidationService<T> {
    boolean isValid(T product);
}
