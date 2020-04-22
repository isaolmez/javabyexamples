package com.javabyexamples.java.tools.lombok.annotations.tostring.recursion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "manager")
public class Developer {

    private String name;
    private String language;
    private Manager manager;
}
