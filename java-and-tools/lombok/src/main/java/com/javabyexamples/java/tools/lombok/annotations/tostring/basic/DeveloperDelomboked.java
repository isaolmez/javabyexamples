package com.javabyexamples.java.tools.lombok.annotations.tostring.basic;

public class DeveloperDelomboked {

    private String name;
    private String language;

    public String toString() {
        return "DeveloperDelomboked(name=" + this.name + ", language=" + this.language + ")";
    }
}
