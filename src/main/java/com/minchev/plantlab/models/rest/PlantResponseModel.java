package com.minchev.plantlab.models.rest;

import com.minchev.plantlab.models.service.PlantServiceModel;
import org.springframework.stereotype.Component;

@Component
public class PlantResponseModel {
    String message;
    boolean flag;
    private PlantServiceModel result;

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

    public void setResult(PlantServiceModel result) {
        this.result= result;
    }

    public PlantServiceModel getResult() {
        return result;
    }
}
