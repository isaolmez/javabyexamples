package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

public class MappingConfiguration {

    public BoundMapperFacade<Person, PersonDto> mapUsingNoNestedMapper() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapUsingNestedMapper() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        mapperFactory.classMap(Pet.class, PetDto.class)
          .field("breed", "kind")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Family, FamilyDto> mapUsingListMembers() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Family.class, FamilyDto.class)
          .field("members[0]", "mother")
          .field("members[1]", "father")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Family.class, FamilyDto.class);
    }
}
