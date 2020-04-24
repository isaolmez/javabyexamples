package com.javabyexamples.spring.core.usingbean.incomponentadvanced;

import lombok.Data;

@Data
public class PersonBean {

    private final String name;
    private int age;
    private PersonBean spouse;
    private String country;

    public PersonBean(String name) {
        this.name = name;
    }

    public PersonBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
