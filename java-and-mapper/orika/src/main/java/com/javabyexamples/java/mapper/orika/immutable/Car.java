package com.javabyexamples.java.mapper.orika.immutable;

public class Car {

    private final String brand;
    private final String horsePower;

    public Car(String brand, String horsePower) {
        this.brand = brand;
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return brand;
    }

    public String getHorsePower() {
        return horsePower;
    }
}
