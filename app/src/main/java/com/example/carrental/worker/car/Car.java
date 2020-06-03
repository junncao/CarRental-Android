package com.example.carrental.worker.car;

public class Car {
    int id;
    String license;
    String brand;
    int state;
    int valid;
    int rent;
    int deposit;

    public Car(int id, String license, String brand, int state, int valid, int rent, int deposit) {
        this.id = id;
        this.license = license;
        this.brand = brand;
        this.state = state;
        this.valid = valid;
        this.rent = rent;
        this.deposit = deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
