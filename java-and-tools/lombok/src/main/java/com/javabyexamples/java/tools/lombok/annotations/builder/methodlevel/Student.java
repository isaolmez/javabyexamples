package com.javabyexamples.java.tools.lombok.annotations.builder.methodlevel;

import lombok.Builder;

public class Student {

    private final String name;
    private final int age;
    private String section;
    private String school;

    public Student(String name, int age, String section, String school) {
        this.name = name;
        this.age = age;
        this.section = section;
        this.school = school;
    }

    @Builder
    public static Student createStudent(String name, int age, String school) {
        return new Student(name, age, school, "");
    }
}
