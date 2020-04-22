package com.javabyexamples.java.tools.lombok.annotations.tostring.inheritance;

import lombok.ToString;

@ToString(callSuper = true)
public class Developer extends Person {

    private String name;
    private String language;
}
