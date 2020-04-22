package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.inheritance;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Employee extends Citizen {

    private String name;
    private int salary;
}
