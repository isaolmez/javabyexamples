package com.javabyexamples.java.mapper.orika.immutable;

public class Driver {

    private final Car car;

    public Driver(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
