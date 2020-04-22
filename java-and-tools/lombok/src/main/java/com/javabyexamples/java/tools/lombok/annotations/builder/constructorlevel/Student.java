package com.javabyexamples.java.tools.lombok.annotations.builder.constructorlevel;

import lombok.Builder;

public class Student {

    private String name;
    private int age;
    private String section;
    private String school;

    @Builder
    public Student(String section, String school) {
        this.section = section;
        this.school = school;
    }
}
