package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import ma.glasnost.orika.BoundMapperFacade;
import org.junit.jupiter.api.Test;

class MappingConfigurationTest {

    private final MappingConfiguration configuration = new MappingConfiguration();

    @Test
    public void testMapUsingNoNestedMapper() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapUsingNoNestedMapper();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getAge()).isEqualTo(person.getAge());

        assertThat(personDto.getPets()).hasSize(person.getPets().size());
        assertThat(personDto.getPets().get(0).getOwnerName()).isEqualTo(person.getPets().get(0).getOwnerName());
        assertThat(personDto.getPets().get(0).getKind()).isNull();
    }

    @Test
    public void testMapUsingNestedMapper() {
        final Person person = PersonHelper.getPerson();
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapUsingNestedMapper();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getName()).isEqualTo(person.getFirstName());
        assertThat(personDto.getSurname()).isEqualTo(person.getLastName());
        assertThat(personDto.getAge()).isEqualTo(person.getAge());

        assertThat(personDto.getPets()).hasSize(person.getPets().size());
        assertThat(personDto.getPets().get(0).getOwnerName()).isEqualTo(person.getPets().get(0).getOwnerName());
        assertThat(personDto.getPets().get(0).getKind()).isEqualTo(person.getPets().get(0).getBreed());
    }

    @Test
    void testMapUsingListMembers() {
        final Family family = new Family();
        family.setMembers(Arrays.asList(PersonHelper.getPerson()));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapUsingListMembers();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getMother().getAge()).isEqualTo(family.getMembers().get(0).getAge());
    }
}
