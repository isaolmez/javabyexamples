package com.javabyexamples.java.mapper.orika.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneOffset;
import ma.glasnost.orika.BoundMapperFacade;
import org.junit.jupiter.api.Test;

class MappingConfigurationTest {

    private final MappingConfiguration configuration = new MappingConfiguration();

    @Test
    void testMapWithMultipleMappers() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithConverter();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getBirthDate().toEpochSecond(ZoneOffset.UTC) * 1000)
          .isEqualTo(person.getBirthDate().getTime());
    }

    @Test
    void testMapWithBidirectionalConverter() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithBidirectionalConverter();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getBirthDate().toEpochSecond(ZoneOffset.UTC) * 1000)
          .isEqualTo(person.getBirthDate().getTime());
    }

    @Test
    void testMapWithBidirectionalConverter_WhenReverse() {
        final PersonDto personDto = PersonHelper.getPersonDto();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithBidirectionalConverter();

        final Person person = mapper.mapReverse(personDto);

        assertThat(person.getAge()).isEqualTo(personDto.getAge());
        assertThat(person.getFirstName()).isEqualTo(personDto.getName());
        assertThat(person.getLastName()).isEqualTo(personDto.getSurname());
        assertThat(person.getBirthDate().getTime())
          .isEqualTo(personDto.getBirthDate().toEpochSecond(ZoneOffset.UTC) * 1000);
    }
}
