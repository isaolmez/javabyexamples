package com.javabyexamples.java.mapper.orika.generics.collections;

import java.util.Objects;

public class PersonDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object that) {
        return Objects.equals(name, ((PersonDto) that).name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}












