package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.include.fieldlevel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Include
    private int age;
    private int salary;
}
