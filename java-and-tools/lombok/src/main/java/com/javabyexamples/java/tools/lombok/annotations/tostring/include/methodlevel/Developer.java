package com.javabyexamples.java.tools.lombok.annotations.tostring.include.methodlevel;

import lombok.ToString;

@ToString
public class Developer {

    private String name;
    private String language;
    private int experienceInYears;

    @ToString.Include
    public boolean isJunior() {
        return experienceInYears < 2;
    }
}
