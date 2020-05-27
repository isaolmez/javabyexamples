package com.javabyexamples.java.mapper.orika.generics.basic;

import com.javabyexamples.java.mapper.orika.generics.collections.Person;

public class AnotherPersonHolder {

    private Person value;

    public Person getValue() {
        return value;
    }

    public void setValue(Person value) {
        this.value = value;
    }
}
