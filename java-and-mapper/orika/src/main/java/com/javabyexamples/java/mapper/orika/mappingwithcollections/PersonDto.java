package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.List;

public class PersonDto {

    private String name;
    private String surname;
    private List<String> petSpecies;
    private List<PetDto> pets;
    private PetDto dog;
    private PetDto cat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(List<String> petSpecies) {
        this.petSpecies = petSpecies;
    }

    public List<PetDto> getPets() {
        return pets;
    }

    public void setPets(List<PetDto> pets) {
        this.pets = pets;
    }

    public PetDto getDog() {
        return dog;
    }

    public void setDog(PetDto dog) {
        this.dog = dog;
    }

    public PetDto getCat() {
        return cat;
    }

    public void setCat(PetDto cat) {
        this.cat = cat;
    }
}
