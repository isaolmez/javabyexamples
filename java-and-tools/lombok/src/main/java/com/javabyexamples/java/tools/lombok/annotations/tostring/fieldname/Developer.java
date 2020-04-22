package com.javabyexamples.java.tools.lombok.annotations.tostring.fieldname;

import lombok.ToString;

@ToString(includeFieldNames = false)
public class Developer {

    private String name;
    private String language;
}
