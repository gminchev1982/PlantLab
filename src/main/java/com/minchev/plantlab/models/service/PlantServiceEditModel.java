package com.minchev.plantlab.models.service;

import org.springframework.stereotype.Component;

@Component
public class PlantServiceEditModel extends BaseServiceModel {
    private String barcode;
    private boolean active;

    public PlantServiceEditModel() {
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
