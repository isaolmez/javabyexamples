package com.javabyexamples.lets.discuss.builder.compareToFluent.fluent;

public class Employee {

    private String firstName;
    private String lastName;
    private int startTime;
    private int endTime;
    private String department;

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Employee startTime(int startTime) {
        this.startTime = startTime;
        return this;
    }

    public Employee endTime(int endTime) {
        this.endTime = endTime;
        return this;
    }

    public Employee department(String department) {
        this.department = department;
        return this;
    }
}
