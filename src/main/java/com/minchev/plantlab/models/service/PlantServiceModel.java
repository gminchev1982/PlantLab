package com.minchev.plantlab.models.service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlantServiceModel extends BaseServiceModel {
    private String barcode;
    private String dateAt;
    private String active;


}
