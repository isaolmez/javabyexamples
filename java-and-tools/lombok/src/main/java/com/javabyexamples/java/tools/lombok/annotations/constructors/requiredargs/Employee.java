package com.javabyexamples.java.tools.lombok.annotations.constructors.requiredargs;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Employee {

    private final String name;
    private int salary;
}

@RequiredArgsConstructor(staticName = "of")
class Department {

    private final String location;
    private final String employeeNumber;
}
