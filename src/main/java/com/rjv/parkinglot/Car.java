package com.rjv.parkinglot;

public class Car {

    private String regNo;
    private String color;

    public Car(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColor() {
        return color;
    }
}