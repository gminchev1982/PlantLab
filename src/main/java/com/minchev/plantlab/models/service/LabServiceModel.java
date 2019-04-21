package com.minchev.plantlab.models.service;

import com.minchev.plantlab.databases.entities.PlantEntity;

public class LabServiceModel extends BaseServiceModel {

        private PlantServiceModel plant;
    private ProductServiceModel  product;
    private UserServiceModel  user;
    private String status;
    private String comment;
    private String quantity;

    public PlantServiceModel getPlant() {
        return plant;
    }

    public void setPlant(PlantServiceModel plant) {
        this.plant = plant;
    }

    public ProductServiceModel getProduct() {
        return product;
    }

    public void setProduct(ProductServiceModel product) {
        this.product = product;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
