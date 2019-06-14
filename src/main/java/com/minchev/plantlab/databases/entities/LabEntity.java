package com.minchev.plantlab.databases.entities;

import com.minchev.plantlab.databases.enums.Health;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table(name = "labs")
@Getter
@Setter
@NoArgsConstructor
public class LabEntity extends BaseEntity {

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private PlantEntity plant;

    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn( name = "product_id",  referencedColumnName = "id" )
    private ProductEntity product;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private Health status;
    private String quantity;
    @Size(max = 250)
    private String comment;
    private Date createdAt;

    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();
    }


}