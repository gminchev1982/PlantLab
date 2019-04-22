package com.minchev.plantlab.models.service;

import com.minchev.plantlab.databases.entities.PlantEntity;

public class LabServiceModel extends BaseServiceModel {

   /*     private PlantServiceModel plant;
    private ProductServiceModel  product;
    private UserServiceModel  user;*/

    private String plantId;
    private String productId;
    private String userId;
    private String status;
    private String comment;
    private String quantity;

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

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
