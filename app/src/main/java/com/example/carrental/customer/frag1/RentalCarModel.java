package com.example.carrental.customer.frag1;

public class RentalCarModel {
    private String id;
    private String license;
    private String brand;
    private String rent;
    private String deposit;

    public RentalCarModel(String id, String license, String brand, String rent, String deposit) {
        this.id = id;
        this.license = license;
        this.brand = brand;
        this.rent = rent;
        this.deposit = deposit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
}
