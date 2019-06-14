package com.minchev.plantlab.databases.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "plants")
@EqualsAndHashCode(callSuper=false)
public class PlantEntity extends BaseEntity {
    @Column(name = "barcode", nullable = false, unique = false, updatable = true, length = 20)
    @Getter @Setter private String barcode;
    private boolean active;
    private Date createdAt;

    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();
        this.active = true;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
