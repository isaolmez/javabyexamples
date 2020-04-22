package com.javabyexamples.java.tools.lombok.annotations.value.accessmodifier;

import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;

@Value
@NonFinal
public class Employee {

    @NonFinal
    @PackagePrivate
    private String name;

    @NonFinal
    @PackagePrivate
    private int salary;
}
