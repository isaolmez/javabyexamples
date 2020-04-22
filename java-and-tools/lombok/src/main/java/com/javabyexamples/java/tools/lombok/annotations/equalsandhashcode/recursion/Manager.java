package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.recursion;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Manager {

    private String name;
    private Employee subordinate;
}
