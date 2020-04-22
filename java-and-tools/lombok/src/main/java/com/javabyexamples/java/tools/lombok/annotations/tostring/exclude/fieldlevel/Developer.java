package com.javabyexamples.java.tools.lombok.annotations.tostring.exclude.fieldlevel;

import lombok.ToString;

@ToString
public class Developer {

    private String name;
    @ToString.Exclude
    private String language;
    @ToString.Exclude
    private int salary;
}
