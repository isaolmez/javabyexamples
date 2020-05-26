package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.Map;

public class Person {

    private String firstName;
    private String lastName;
    private Map<String, Pet> petsBySpecies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Pet> getPetsBySpecies() {
        return petsBySpecies;
    }

    public void setPetsBySpecies(Map<String, Pet> petsBySpecies) {
        this.petsBySpecies = petsBySpecies;
    }
}
