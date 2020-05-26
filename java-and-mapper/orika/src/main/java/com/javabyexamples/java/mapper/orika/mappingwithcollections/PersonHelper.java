package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.HashMap;
import java.util.Map;

public class PersonHelper {

    public static Person getPerson() {
        return getPerson("john");
    }

    public static Person getPerson(String name) {
        final Person person = new Person();
        person.setFirstName(name);
        person.setLastName("doe");
        final Pet pet = new Pet();
        pet.setName("tom");
        pet.setSpecies("cat");
        final Pet pet2 = new Pet();
        pet2.setName("rocky");
        pet2.setSpecies("dog");
        final Map<String, Pet> pets = new HashMap<>();
        pets.put(pet.getSpecies(), pet);
        pets.put(pet2.getSpecies(), pet2);
        person.setPetsBySpecies(pets);
        return person;
    }
}
