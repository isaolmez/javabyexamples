package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.recursion;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = "manager")
public class Employee {

    private String name;
    private int salary;
    private Manager manager;
}
