package com.javabyexamples.java.mapper.orika.immutable;

public class DriverDto {

    private final Car car;

    public DriverDto(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
