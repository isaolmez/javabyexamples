package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.List;
import java.util.Map;

public class FamilyDto {

    private List<PersonDto> parents;
    private PersonDto mother;
    private PersonDto father;
    private String motherName;
    private String fatherName;
    private List<String> parentNames;
    private Map<String, PersonDto> parentsByName;

    public List<PersonDto> getParents() {
        return parents;
    }

    public void setParents(List<PersonDto> parents) {
        this.parents = parents;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public List<String> getParentNames() {
        return parentNames;
    }

    public void setParentNames(List<String> parentNames) {
        this.parentNames = parentNames;
    }

    public Map<String, PersonDto> getParentsByName() {
        return parentsByName;
    }

    public void setParentsByName(
      Map<String, PersonDto> parentsByName) {
        this.parentsByName = parentsByName;
    }

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
