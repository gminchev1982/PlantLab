package com.minchev.plantlab.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Plant not found!")
public class PlantNotFoundException extends RuntimeException {

    private int statusCode;

    public PlantNotFoundException() {
        this.statusCode = 404;
    }

    public PlantNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
