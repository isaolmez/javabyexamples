package com.javabyexamples.java.tools.lombok.annotations.constructors.noargs;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Employee {

    private String name;
    private int salary;
}

@NoArgsConstructor(staticName = "of")
class Department {

    private String location;
    private String employeeNumber;
}
