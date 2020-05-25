package com.javabyexamples.java.mapper.orika.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import ma.glasnost.orika.BoundMapperFacade;
import org.junit.jupiter.api.Test;

class MappingConfigurationTest {

    private final MappingConfiguration configuration = new MappingConfiguration();

    @Test
    void testMapWithDefaults() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithDefaults();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isNull();
        assertThat(personDto.getSurname()).isNull();
    }

    @Test
    void testMapWithoutDefaults() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithoutDefaults();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(0);
        assertThat(personDto.getName()).isNull();
        assertThat(personDto.getSurname()).isNull();
    }

    @Test
    void testMapWithFields() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithFields();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    void testMapWithFieldsOneWay() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithFieldsOneWay();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getAge()).isEqualTo(0);
    }

    @Test
    void testMapWithCustomMapper() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithCustomMapper();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    void testMapWithConstructor() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, ImmutablePersonDto> mapper = configuration.mapWithConstructor();

        final ImmutablePersonDto personTarget = mapper.map(person);

        assertThat(personTarget.getAge()).isEqualTo(person.getAge());
        assertThat(personTarget.getName()).isEqualTo(person.getFirstName());
        assertThat(personTarget.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    void testMapWithNestedObjects() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithNestedObjects();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getCity()).isEqualTo(person.getAddress().getCity());
    }

    @Test
    void testMapWithMultipleMappers() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapWithMultipleMappers();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getWorkAddress().getCity()).isEqualTo(person.getWorkAddress().getCity());
        assertThat(personDto.getWorkAddress().getZipCode()).isEqualTo(person.getWorkAddress().getPostalCode());
    }
}
