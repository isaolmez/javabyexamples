package com.javabyexamples.java.tools.lombok.annotations.constructors.requiredargs;

public class EmployeeDelomboked {

    private final String name;
    private int salary;

    @java.beans.ConstructorProperties({"name"})
    public EmployeeDelomboked(String name) {
        this.name = name;
    }
}
