package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.exclude.classlevel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = {"age", "salary"})
public class Employee {

    private String name;
    private int age;
    private int salary;
}
