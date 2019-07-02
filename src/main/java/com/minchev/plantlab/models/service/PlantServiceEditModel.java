package com.minchev.plantlab.models.service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlantServiceEditModel extends BaseServiceModel {


    private String barcode;
    private boolean active;


}
