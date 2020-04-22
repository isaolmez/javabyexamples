package com.javabyexamples.java.tools.lombok.annotations.tostring.exclude.classlevel;

import lombok.ToString;

@ToString(exclude = {"language", "salary"})
public class Developer {

    private String name;
    private String language;
    private int salary;
}
