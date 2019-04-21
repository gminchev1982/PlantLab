package com.minchev.plantlab.models.forms;

public class ProductEditForm {
    private String _csrf;
    private String name;
    private String nameold;
    private String active;
    private String id;

    public String get_csrf() {
        return _csrf;
    }

    public void set_csrf(String _csrf) {
        this._csrf = _csrf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameold() {
        return nameold;
    }

    public void setNameold(String nameold) {
        this.nameold = nameold;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
