package com.minchev.plantlab.models.forms;


public class PlantEditForm {
    private String _csrf;
    private String barcode;
    private String barcodeold;
    private String active;
    private String id;
    private String dateAt;


    public String get_csrf() {
        return _csrf;
    }

    public void set_csrf(String _csrf) {
        this._csrf = _csrf;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcodeold() {
        return barcodeold;
    }

    public void setBarcodeold(String barcodeold) {
        this.barcodeold = barcodeold;
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

    public String getDateAt() {
        return dateAt;
    }

    public void setDateAt(String dateAt) {
        this.dateAt = dateAt;
    }
}
