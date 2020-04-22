package com.javabyexamples.java.tools.lombok.annotations.tostring.include.classlevel;

import lombok.ToString;

@ToString(of = {"name", "experienceInYears"})
public class Developer {

    private String name;
    private String language;
    private int experienceInYears;
}
