package com.javabyexamples.java.mapper.orika.mappingwithcollections;

public class FamilyDto {

    private PersonDto mother;
    private PersonDto father;

    public PersonDto getMother() {
        return mother;
    }

    public void setMother(PersonDto mother) {
        this.mother = mother;
    }

    public PersonDto getFather() {
        return father;
    }

    public void setFather(PersonDto father) {
        this.father = father;
    }
}
