package com.javabyexamples.java.tools.lombok.annotations.constructors.allargs;

public class EmployeeDelomboked {

    private String name;
    private int salary;

    public EmployeeDelomboked(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}

class DepartmentDelomboked {

    private String location;
    private String employeeNumber;

    private DepartmentDelomboked(String location, String employeeNumber) {
        this.location = location;
        this.employeeNumber = employeeNumber;
    }

    public static DepartmentDelomboked of(String location, String employeeNumber) {
        return new DepartmentDelomboked(location, employeeNumber);
    }
}

