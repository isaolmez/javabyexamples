package com.javabyexamples.spring.core.spel.basic;

import lombok.Data;

@Data
public class Person {

    private String name;
    private Address address = new Address();
}
