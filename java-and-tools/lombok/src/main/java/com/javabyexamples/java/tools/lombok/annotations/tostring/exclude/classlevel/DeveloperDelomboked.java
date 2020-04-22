package com.javabyexamples.java.tools.lombok.annotations.tostring.exclude.classlevel;

public class DeveloperDelomboked {

    private String name;
    private String language;
    private int salary;

    public String toString() {
        return "DeveloperDelomboked(name=" + this.name + ")";
    }
}
