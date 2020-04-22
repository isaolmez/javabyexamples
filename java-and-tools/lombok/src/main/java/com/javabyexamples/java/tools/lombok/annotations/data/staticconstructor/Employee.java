package com.javabyexamples.java.tools.lombok.annotations.data.staticconstructor;

import lombok.Data;

@Data(staticConstructor = "of")
public class Employee {

    private String name;
    private int salary;
}
