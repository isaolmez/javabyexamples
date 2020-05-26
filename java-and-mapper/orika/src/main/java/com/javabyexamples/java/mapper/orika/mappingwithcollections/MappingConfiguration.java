package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

public class MappingConfiguration {

    public BoundMapperFacade<Family, FamilyDto> mapListFields() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .byDefault()
          .register();

        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }

    public BoundMapperFacade<Family, FamilyDto> mapUsingListElements() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .field("parents[0]", "mother")
          .field("parents[1]", "father")
          .byDefault()
          .register();

        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }

    public BoundMapperFacade<Family, FamilyDto> mapUsingListElementsWithNestedAccess() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .field("parents[0].firstName", "motherName")
          .field("parents[1].firstName", "fatherName")
          .byDefault()
          .register();

        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }

    public BoundMapperFacade<Family, FamilyDto> mapUsingListElementsInBulk() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .field("parents{firstName}", "parentNames{}")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapUsingMapElements() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .field("petsBySpecies['cat']", "cat")
          .field("petsBySpecies[\"dog\"]", "dog")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Family, FamilyDto> mapUsingMapElementsInBulk() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .field("parents{firstName}", "parentsByName{key}")
          .field("parents{}", "parentsByName{value}")
          .byDefault()
          .register();

        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapAssigningMapElementsToListElements() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .field("petsBySpecies{key}", "petSpecies{}")
          .field("petsBySpecies{value}", "pets{}")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }
}
