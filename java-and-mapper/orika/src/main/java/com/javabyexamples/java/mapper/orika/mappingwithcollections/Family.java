package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.List;

public class Family {

    private List<Person> parents;

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }
}
