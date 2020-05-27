package com.javabyexamples.java.mapper.orika.generics.collections;

import java.util.ArrayList;
import java.util.List;

public class PersonGroupDto {

    private List<PersonDto> members = new ArrayList<>();

    public List<PersonDto> getMembers() {
        return members;
    }

    public void setMembers(List<PersonDto> members) {
        this.members = members;
    }
}
