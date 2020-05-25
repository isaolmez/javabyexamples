package com.javabyexamples.java.mapper.orika.basic;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BasicMapperConfiguration {

    public static void main(String[] args) {
        final Person person = PersonHelper.getPerson();

        final BasicMapperConfiguration basicMapperConfiguration = new BasicMapperConfiguration();
        basicMapperConfiguration.mapWithMapperFacade(person);
        basicMapperConfiguration.mapWithBoundedMapperFacade(person);
    }

    public PersonDto mapWithMapperFacade(Person person) {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        return mapperFacade.map(person, PersonDto.class);
    }

    public PersonDto mapExistingWithMapperFacade(Person person, PersonDto existingPersonDto) {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        mapperFacade.map(person, existingPersonDto);
        return existingPersonDto;
    }

    public PersonDto mapWithBoundedMapperFacade(Person person) {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final BoundMapperFacade<Person, PersonDto> boundMapper = mapperFactory
          .getMapperFacade(Person.class, PersonDto.class);

        return boundMapper.map(person);
    }

    public PersonDto mapExistingWithBoundedMapperFacade(Person person,
      PersonDto existingPersonDto) {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final BoundMapperFacade<Person, PersonDto> boundMapper = mapperFactory
          .getMapperFacade(Person.class, PersonDto.class);

        boundMapper.map(person, existingPersonDto);
        return existingPersonDto;
    }
}
