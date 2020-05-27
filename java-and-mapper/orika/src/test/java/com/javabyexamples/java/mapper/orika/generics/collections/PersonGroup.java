package com.javabyexamples.java.mapper.orika.generics.collections;

import java.util.ArrayList;
import java.util.List;

public class PersonGroup {

    private List<Person> members = new ArrayList<>();

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }
}
