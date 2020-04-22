package com.javabyexamples.java.tools.lombok.annotations.value.staticconstructor;

import lombok.Value;

@Value(staticConstructor = "of")
public class Employee {

    private String name;
    private int salary;
}
