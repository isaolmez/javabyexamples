package com.javabyexamples.java.tools.lombok.annotations.constructors.allargs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Employee {

    private String name;
    private int salary;
}

@AllArgsConstructor(staticName = "of")
class Department {

    private String location;
    private String employeeNumber;
}
