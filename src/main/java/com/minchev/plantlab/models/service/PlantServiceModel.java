package com.minchev.plantlab.models.service;

public class PlantServiceModel  extends BaseServiceModel {
    private String barcode;
    private String dateAt;
    private String active;


    public PlantServiceModel() {
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDateAt() {
        return dateAt;
    }

    public void setDateAt(String dateAt) {
        this.dateAt = dateAt;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
