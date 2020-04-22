package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.include.methodlevel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Employee {

    private String name;
    private int age;
    private int salary;

    @EqualsAndHashCode.Include
    public boolean canDrink() {
        return age > 18;
    }
}
