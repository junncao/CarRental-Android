package com.example.carrental.customer.frag3;

public class ReturnCarModel {
    private String rentalID;
    private String license;
    private String brand;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ReturnCarModel(String rentalID, String license, String brand,int state) {
        this.rentalID = rentalID;
        this.license = license;
        this.brand = brand;
        this.state=state;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
