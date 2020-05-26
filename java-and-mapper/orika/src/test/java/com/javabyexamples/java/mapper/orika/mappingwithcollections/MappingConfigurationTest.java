package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import org.junit.jupiter.api.Test;

class MappingConfigurationTest {

    private final MappingConfiguration configuration = new MappingConfiguration();

    @Test
    void testMapListFields() {
        final Family family = new Family();
        final Person mother = PersonHelper.getPerson("jane");
        final Person father = PersonHelper.getPerson("john");
        family.setParents(Arrays.asList(mother, father));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapListFields();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getParents().get(0).getName()).isEqualTo(family.getParents().get(0).getFirstName());
        assertThat(familyDto.getParents().get(0).getSurname()).isEqualTo(family.getParents().get(0).getLastName());
        assertThat(familyDto.getParents().get(1).getName()).isEqualTo(family.getParents().get(1).getFirstName());
        assertThat(familyDto.getParents().get(1).getSurname()).isEqualTo(family.getParents().get(1).getLastName());
    }

    @Test
    void testMapUsingListElements() {
        final Family family = new Family();
        final Person mother = PersonHelper.getPerson("jane");
        final Person father = PersonHelper.getPerson("john");
        family.setParents(Arrays.asList(mother, father));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapUsingListElements();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getMother().getName()).isEqualTo(family.getParents().get(0).getFirstName());
        assertThat(familyDto.getFather().getName()).isEqualTo(family.getParents().get(1).getFirstName());
    }

    @Test
    void testMapUsingListElementsWithNestedAccess() {
        final Family family = new Family();
        final Person mother = PersonHelper.getPerson("jane");
        final Person father = PersonHelper.getPerson("john");
        family.setParents(Arrays.asList(mother, father));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapUsingListElementsWithNestedAccess();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getMotherName()).isEqualTo(family.getParents().get(0).getFirstName());
        assertThat(familyDto.getFatherName()).isEqualTo(family.getParents().get(1).getFirstName());
    }

    @Test
    void testMapUsingListElementsInBulk() {
        final Family family = new Family();
        final Person mother = PersonHelper.getPerson("jane");
        final Person father = PersonHelper.getPerson("john");
        family.setParents(Arrays.asList(mother, father));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapUsingListElementsInBulk();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getParentNames()).containsExactly(mother.getFirstName(), father.getFirstName());
    }

    @Test
    void testMapUsingMapElements() {
        final Person person = PersonHelper.getPerson("jane");
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapUsingMapElements();

        final PersonDto personDto = mapper.map(person);

        assertThat(personDto.getCat().getName()).isEqualTo(person.getPetsBySpecies().get("cat").getName());
        assertThat(personDto.getDog().getName()).isEqualTo(person.getPetsBySpecies().get("dog").getName());
    }

    @Test
    void testMapUsingMapElementsInBulk() {
        final Family family = new Family();
        final Person mother = PersonHelper.getPerson("jane");
        final Person father = PersonHelper.getPerson("john");
        family.setParents(Arrays.asList(mother, father));
        final BoundMapperFacade<Family, FamilyDto> mapper = configuration.mapUsingMapElementsInBulk();

        final FamilyDto familyDto = mapper.map(family);

        assertThat(familyDto.getParentsByName().keySet()).containsOnly("jane", "john");
        assertThat(familyDto.getParentsByName().get("jane").getName()).isEqualTo("jane");
        assertThat(familyDto.getParentsByName().get("john").getName()).isEqualTo("john");
    }

    @Test
    void testMapAssigningMapElementsToListElements() {
        final Person person = PersonHelper.getPerson("jane");
        final BoundMapperFacade<Person, PersonDto> mapper = configuration.mapAssigningMapElementsToListElements();

        final PersonDto personDto = mapper.map(person);

        assertThat(new HashSet<>(personDto.getPetSpecies())).isEqualTo(person.getPetsBySpecies().keySet());
    }

    @Test
    void testMapMultipleTimes() {
        final DefaultMapperFactory mapperFactory = new Builder().build();
        mapperFactory.classMap(Person.class, PersonDto.class)
          .field("firstName", "name")
          .field("lastName", "surname")
          .byDefault()
          .register();
        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        final List<Person> people = Arrays.asList(PersonHelper.getPerson("jane"), PersonHelper.getPerson("john"));
        final List<PersonDto> personDtos = mapperFacade.mapAsList(people, PersonDto.class);

        assertThat(personDtos.get(0).getName()).isEqualTo(people.get(0).getFirstName());
        assertThat(personDtos.get(1).getName()).isEqualTo(people.get(1).getFirstName());
    }
}
