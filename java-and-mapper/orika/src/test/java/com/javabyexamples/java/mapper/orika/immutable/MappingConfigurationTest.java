package com.javabyexamples.java.mapper.orika.immutable;

import static org.assertj.core.api.Assertions.assertThat;

import ma.glasnost.orika.BoundMapperFacade;
import org.junit.jupiter.api.Test;

class MappingConfigurationTest {

    private final MappingConfiguration configuration = new MappingConfiguration();

@Test
public void testMapWithConstructor() {
    final BoundMapperFacade<Car, CarDto> mapper = configuration.mapWithConstructor();
    final Car car = new Car("Ferrari", "950");

    final CarDto carDto = mapper.map(car);

    assertThat(carDto.getBrand()).isEqualTo(car.getBrand());
    assertThat(carDto.getPower()).isEqualTo(car.getHorsePower());
    assertThat(carDto.getYear()).isNull();

    final Car car1 = mapper.mapReverse(carDto);
    assertThat(car1.getBrand()).isEqualTo(carDto.getBrand());
    assertThat(car1.getHorsePower()).isEqualTo(carDto.getPower());
}

@Test
public void testMapWithConstructorSelection() {
    final BoundMapperFacade<Car, CarDto> mapper = configuration.mapWithConstructorSelection();
    final Car car = new Car("Ferrari", "950");

    final CarDto carDto = mapper.map(car);

    assertThat(carDto.getBrand()).isEqualTo(car.getBrand());
    assertThat(carDto.getPower()).isEqualTo(car.getHorsePower());
    assertThat(carDto.getYear()).isNull();
}

@Test
public void testMapWithObjectFactory() {
    final BoundMapperFacade<Car, CarDto> mapper = configuration.mapWithObjectFactory();
    final Car car = new Car("Ferrari", "950");

    final CarDto carDto = mapper.map(car);

    assertThat(carDto.getBrand()).isEqualTo(car.getBrand());
    assertThat(carDto.getPower()).isEqualTo(car.getHorsePower());
    assertThat(carDto.getYear()).isEqualTo("2000");
}

@Test
public void testMapWithPassThroughConverter() {
    final BoundMapperFacade<Driver, DriverDto> mapper = configuration.mapWithPassThroughConverter();
    final Car car = new Car("Ferrari", "950");
    final Driver driver = new Driver(car);

    final DriverDto driverDto = mapper.map(driver);

    assertThat(driverDto.getCar() == driver.getCar()).isTrue();
    assertThat(driverDto.getCar().getBrand()).isEqualTo(car.getBrand());
    assertThat(driverDto.getCar().getHorsePower()).isEqualTo(car.getHorsePower());
}
}
