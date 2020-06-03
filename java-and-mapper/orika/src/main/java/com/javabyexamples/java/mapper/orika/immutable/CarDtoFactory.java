package com.javabyexamples.java.mapper.orika.immutable;


import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;

public class CarDtoFactory implements ObjectFactory<CarDto> {

    @Override
    public CarDto create(Object source, MappingContext mappingContext) {
        if (source instanceof Car) {
            final Car car = (Car) source;
            return new CarDto(car.getBrand(), car.getHorsePower(), "2000");
        }

        return null; //
    }
}
