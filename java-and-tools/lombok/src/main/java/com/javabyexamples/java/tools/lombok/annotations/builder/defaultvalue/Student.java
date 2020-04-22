package com.javabyexamples.java.tools.lombok.annotations.builder.defaultvalue;

import lombok.Builder;

@Builder
public class Student {

    private String name;
    private int age;
    @Builder.Default
    private String teacher = "Mrs. White";
}
