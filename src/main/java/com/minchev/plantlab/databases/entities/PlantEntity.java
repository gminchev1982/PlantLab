package com.minchev.plantlab.databases.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "plants")
public class PlantEntity extends BaseEntity {

    private String barcode;
    private boolean active=true;
    private Date createdAt;

    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();

    }
    public PlantEntity() {
    }

    @Column(name = "barcode", nullable = false, unique = true, updatable = true, length = 20)
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
