package com.minchev.plantlab.models.rest;

import org.springframework.stereotype.Component;

@Component
public class PlantResponseModel {
    String message;
    boolean flag;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
