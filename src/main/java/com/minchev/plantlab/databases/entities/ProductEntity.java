package com.minchev.plantlab.databases.entities;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class ProductEntity  extends  BaseEntity{
    @NonNull
    private String name;
    @NonNull
    private boolean active;
    @NonNull
    private Date createdAt;
    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();
        this.active = true;


    }

    public ProductEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
