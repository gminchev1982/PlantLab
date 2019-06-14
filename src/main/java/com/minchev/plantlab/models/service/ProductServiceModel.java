package com.minchev.plantlab.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel extends BaseServiceModel {
    private String name;
    private String dateAt;
    private String active;
}
