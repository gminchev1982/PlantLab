package com.minchev.plantlab.models.service;

import org.springframework.stereotype.Component;


public class PlantServiceEditModel extends BaseServiceModel {
    private String id;
    private String barcode;
    private boolean active;

    public PlantServiceEditModel() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
