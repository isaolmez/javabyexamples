package com.javabyexamples.java.mapper.orika.mapping;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import ma.glasnost.orika.metadata.MappingDirection;

public class MappingConfiguration {

    public BoundMapperFacade<Person, PersonDto> mapWithoutDefaults() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithDefaults() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithFields() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithFieldsDetailed() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .fieldMap("firstName", "name").direction(MappingDirection.BIDIRECTIONAL).add()
          .fieldMap("lastName", "surname").direction(MappingDirection.BIDIRECTIONAL).add()
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithNestedObjects() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .field("address.city", "city")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithMultipleMappers() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        mapperFactory.classMap(Address.class, AddressDto.class)
          .field("postalCode", "zipCode")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapByExcludingFields() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .exclude("age")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithFieldsOneWay() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .fieldAToB("firstName", "name")
          .fieldAToB("lastName", "surname")
          .fieldBToA("age", "age")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithCustomMapper() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .customize(new CustomMapper<Person, PersonDto>() {
              @Override
              public void mapAtoB(Person person, PersonDto personDto, MappingContext context) {
                  if (person.getAge() > 21) {
                      personDto.setAge(person.getAge());
                  }
              }
          })
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, ImmutablePersonDto> mapWithConstructor() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, ImmutablePersonDto.class)
          .field("firstName", "name")
          .fieldAToB("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, ImmutablePersonDto.class);
    }
}
