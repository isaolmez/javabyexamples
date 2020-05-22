package com.javabyexamples.java.mapper.orika.basic;

import com.javabyexamples.java.mapper.orika.shared.PersonHelper;
import com.javabyexamples.java.mapper.orika.shared.PersonSource;
import com.javabyexamples.java.mapper.orika.shared.PersonTarget;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

public class BasicMapperConfiguration {

    private final PersonSource personSource = PersonHelper.getPersonSource();
    private final PersonTarget existingPersonTarget = PersonHelper.getPersonTarget();

    public static void main(String[] args) {
        final BasicMapperConfiguration basicMapperConfiguration = new BasicMapperConfiguration();
        basicMapperConfiguration.mapWithMapperFacade();
        basicMapperConfiguration.mapWithBoundedMapperFacade();
    }

    public void mapWithMapperFacade() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .byDefault()
          .register();
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        final PersonTarget personTarget = mapperFacade.map(personSource, PersonTarget.class);
        System.out.println(personTarget);

        mapperFacade.map(personSource, existingPersonTarget);
        System.out.println(existingPersonTarget);
    }

    public void mapWithBoundedMapperFacade() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(PersonSource.class, PersonTarget.class)
          .byDefault()
          .register();
        final BoundMapperFacade<PersonSource, PersonTarget> mapperFacade = mapperFactory
          .getMapperFacade(PersonSource.class, PersonTarget.class);

        final PersonTarget personTarget = mapperFacade.map(personSource);
        System.out.println(personTarget);

        mapperFacade.map(personSource, existingPersonTarget);
        System.out.println(existingPersonTarget);
    }
}
