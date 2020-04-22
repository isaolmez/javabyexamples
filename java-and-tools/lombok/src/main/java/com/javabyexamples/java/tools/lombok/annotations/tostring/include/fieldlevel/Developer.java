package com.javabyexamples.java.tools.lombok.annotations.tostring.include.fieldlevel;

import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
public class Developer {

    @ToString.Include
    private String name;
    private String language;
    @ToString.Include
    private int experienceInYears;
}
