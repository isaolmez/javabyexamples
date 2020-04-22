package com.javabyexamples.java.tools.lombok.annotations.tostring.recursion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Manager {

    private String name;
    private Developer subordinate;
}
