package com.minchev.plantlab.models.service;

import lombok.Data;

@Data
public class PlantServiceModel  extends BaseServiceModel {
    private String barcode;
    private String dateAt;
    private String active;


}
