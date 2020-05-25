package com.javabyexamples.java.mapper.orika.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BasicMapperConfigurationTest {

    private final BasicMapperConfiguration configuration = new BasicMapperConfiguration();

    @Test
    public void testMapWithMapperFacade(){
        final Person person = PersonHelper.getPerson();

        final PersonDto personDto = configuration.mapWithMapperFacade(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    public void testMapExistingWithMapperFacade(){
        final Person person = PersonHelper.getPerson();
        final PersonDto personDto = new PersonDto();

        configuration.mapExistingWithMapperFacade(person, personDto);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    public void testMapWithBoundedMapperFacade(){
        final Person person = PersonHelper.getPerson();

        final PersonDto personDto = configuration.mapWithBoundedMapperFacade(person);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }

    @Test
    public void testMapExistingWithBoundedMapperFacade(){
        final Person person = PersonHelper.getPerson();
        final PersonDto personDto = new PersonDto();

        configuration.mapExistingWithBoundedMapperFacade(person, personDto);

        assertThat(personDto.getAge()).isEqualTo(person.getAge());
        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
    }
}
