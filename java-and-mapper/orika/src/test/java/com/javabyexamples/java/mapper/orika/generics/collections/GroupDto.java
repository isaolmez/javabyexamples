package com.javabyexamples.java.mapper.orika.generics.collections;

import java.util.ArrayList;
import java.util.List;

public class GroupDto<P> {

    private List<P> members = new ArrayList<>();

    public List<P> getMembers() {
        return members;
    }

    public void setMembers(List<P> members) {
        this.members = members;
    }
}
