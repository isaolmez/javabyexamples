package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.Arrays;

public class PersonHelper {

    public static Person getPerson() {
        final Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setAge(25);
        final Pet pet = new Pet();
        pet.setOwnerName(person.getFirstName());
        pet.setBreed("stray cat");
        person.setPets(Arrays.asList(pet));
        return person;
    }
}
