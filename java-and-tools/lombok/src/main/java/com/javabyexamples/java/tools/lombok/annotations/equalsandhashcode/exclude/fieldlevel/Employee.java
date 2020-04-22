package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.exclude.fieldlevel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Employee {

    private String name;
    @EqualsAndHashCode.Exclude
    private int age;
    @EqualsAndHashCode.Exclude
    private int salary;
}
