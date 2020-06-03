package com.javabyexamples.java.mapper.orika.immutable;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

public class MappingConfiguration {

    public BoundMapperFacade<Car, CarDto> mapWithConstructor() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Car.class, CarDto.class)
          .field("horsePower", "power")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Car.class, CarDto.class);
    }

    public BoundMapperFacade<Car, CarDto> mapWithConstructorSelection() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Car.class, CarDto.class)
          .field("horsePower", "power")
          .constructorA("brand", "horsePower")
          .constructorB("brand", "power")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Car.class, CarDto.class);
    }

    public BoundMapperFacade<Car, CarDto> mapWithObjectFactory() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.registerObjectFactory(new CarDtoFactory(), CarDto.class);
        mapperFactory.classMap(Car.class, CarDto.class)
          .field("horsePower", "power")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Car.class, CarDto.class);
    }

    public BoundMapperFacade<Driver, DriverDto> mapWithPassThroughConverter() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(Car.class));
        mapperFactory.classMap(Driver.class, DriverDto.class)
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Driver.class, DriverDto.class);
    }
}
