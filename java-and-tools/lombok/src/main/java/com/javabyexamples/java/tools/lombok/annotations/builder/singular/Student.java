package com.javabyexamples.java.tools.lombok.annotations.builder.singular;

import java.util.Set;
import lombok.Builder;
import lombok.Singular;

@Builder
public class Student {

    private String name;
    private int age;
    @Singular
    private Set<String> lessons;
}
