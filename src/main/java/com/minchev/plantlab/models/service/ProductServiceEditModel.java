package com.minchev.plantlab.models.service;

public class ProductServiceEditModel {
    private String id;
    private String name;
    private boolean active;

    public ProductServiceEditModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
