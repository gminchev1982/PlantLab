package com.minchev.plantlab.databases.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity  extends  BaseEntity{
    @NonNull
    private String name;
    @NonNull
    private boolean active;
    private Date createdAt;
    @PrePersist
    private void initializeCreatedAt() {
        this.createdAt = new Date();
        this.active = true;
    }

    public boolean isActive() {
        return  this.active;
    }
}
