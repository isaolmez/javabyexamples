package com.javabyexamples.java.mapper.orika.converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import ma.glasnost.orika.metadata.Type;

public class MappingConfiguration {

    public BoundMapperFacade<Person, PersonDto> mapWithConverter() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new DateToLocalDateTimeConverter());
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public BoundMapperFacade<Person, PersonDto> mapWithBidirectionalConverter() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new DateAndLocalDateTimeConverter());
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        return mapperFactory.getMapperFacade(Person.class, PersonDto.class);
    }

    public static class DateToLocalDateTimeConverter extends CustomConverter<Date, LocalDateTime> {

        @Override
        public LocalDateTime convert(Date source, Type<? extends LocalDateTime> destinationType,
          MappingContext mappingContext) {
            return LocalDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
        }
    }

    public static class DateAndLocalDateTimeConverter extends BidirectionalConverter<Date, LocalDateTime> {

        @Override
        public LocalDateTime convertTo(Date source, Type<LocalDateTime> destinationType,
          MappingContext mappingContext) {
            return LocalDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
        }

        @Override
        public Date convertFrom(LocalDateTime source, Type<Date> destinationType, MappingContext mappingContext) {
            return Date.from(source.toInstant(ZoneOffset.UTC));
        }
    }
}
