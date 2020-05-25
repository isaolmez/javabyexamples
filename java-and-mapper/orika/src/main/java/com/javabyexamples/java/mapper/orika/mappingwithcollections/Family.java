package com.javabyexamples.java.mapper.orika.mappingwithcollections;

import java.util.List;

public class Family {

    private List<Person> members;

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }
}
