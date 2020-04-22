package com.javabyexamples.java.tools.lombok.annotations.nonnull.field;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Company {

    @NonNull
    private String location;
}
