package com.javabyexamples.java.mapper.orika.immutable;

public class CarDto {

    private final String brand;
    private final String power;
    private final String year;

    public CarDto(String brand, String power) {
        this.brand = brand;
        this.power = power;
        this.year = null;
    }

    public CarDto(String brand, String power, String year) {
        this.brand = brand;
        this.power = power;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getPower() {
        return power;
    }

    public String getYear() {
        return year;
    }
}
