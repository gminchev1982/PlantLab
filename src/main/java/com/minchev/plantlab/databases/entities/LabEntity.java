package com.minchev.plantlab.databases.entities;

import com.minchev.plantlab.databases.enums.Health;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table(name = "labs")
public class LabEntity extends BaseEntity {


    private PlantEntity plant;
    private ProductEntity product;
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private Health status;
    private String quantity;
    private String comment;
    private Date createdAt;

    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();
    }

    public LabEntity() {
    }

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(
            name = "plant_id",
            referencedColumnName = "id"
    )
    public PlantEntity getPlant() {
        return plant;
    }

    public void setPlant(PlantEntity plant) {
        this.plant = plant;
    }
    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Health getStatus() {
        return status;
    }

    public void setStatus(Health status) {
        this.status = status;
    }
    @Size(max = 250)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}