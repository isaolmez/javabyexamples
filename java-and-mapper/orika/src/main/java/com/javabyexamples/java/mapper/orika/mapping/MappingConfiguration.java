package com.javabyexamples.java.mapper.orika.mapping;

import com.javabyexamples.java.mapper.orika.shared.ImmutablePersonTarget;
import com.javabyexamples.java.mapper.orika.shared.PersonHelper;
import com.javabyexamples.java.mapper.orika.shared.PersonSource;
import com.javabyexamples.java.mapper.orika.shared.PersonTarget;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import ma.glasnost.orika.metadata.Type;

public class MappingConfiguration {

    private final PersonSource personSource = PersonHelper.getPersonSource();

    public static void main(String[] args) {
        final MappingConfiguration mappingConfiguration = new MappingConfiguration();
        mappingConfiguration.mapWithoutDefaults();
        mappingConfiguration.mapWithDefaults();
        mappingConfiguration.mapWithFields();
        mappingConfiguration.mapWithFieldsOneWay();
        mappingConfiguration.mapWithCustomMapper();
        mappingConfiguration.mapWithConstructor();
        mappingConfiguration.mapWithConverter();
    }

    public void mapWithDefaults() {
        System.out.println("mapWithDefaults");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithoutDefaults() {
        System.out.println("mapWithoutDefaults");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithFields() {
        System.out.println("mapWithFields");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithFieldsOneWay() {
        System.out.println("mapWithFieldsOneWay");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .fieldAToB("firstName", "name")
          .fieldAToB("lastName", "surname")
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithCustomMapper() {
        System.out.println("mapWithCustomMapper");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .field("firstName", "name")
          .customize(new CustomMapper<PersonSource, PersonTarget>() {
              @Override
              public void mapAtoB(PersonSource personSource, PersonTarget personTarget, MappingContext context) {
                  personTarget.setSurname(personSource.getLastName());
              }
          })
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithConstructor() {
        System.out.println("mapWithConstructor");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, ImmutablePersonTarget.class)
          .field("firstName", "name")
          .fieldAToB("lastName", "surname")
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, ImmutablePersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, ImmutablePersonTarget.class);

        executeMapping(mapperFacade);
    }

    public void mapWithConverter() {
        class MyConverter extends CustomConverter<String, String> {

            @Override
            public String convert(String source, Type<? extends String> destinationType,
              MappingContext mappingContext) {
                return source;
            }
        }

        System.out.println("mapWithConverter");
        final DefaultMapperFactory mapperFactory = new Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter("convert1", new MyConverter());
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .field("firstName", "name")
          .fieldMap("lastName", "surname").converter("convert1").add()
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        executeMapping(mapperFacade);
    }

    private <T> void executeMapping(BoundMapperFacade<PersonSource, T> mapperFacade) {
        final T mapped = mapperFacade.map(personSource);
        System.out.println(mapped);
    }
}
