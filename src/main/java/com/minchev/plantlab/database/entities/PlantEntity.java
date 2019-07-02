package com.minchev.plantlab.database.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "plants")
@EqualsAndHashCode(callSuper = false)
public class PlantEntity extends BaseEntity {
    @Column(name = "barcode", nullable = false, unique = false, updatable = true, length = 20)
    @Getter
    @Setter
    private String barcode;
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
