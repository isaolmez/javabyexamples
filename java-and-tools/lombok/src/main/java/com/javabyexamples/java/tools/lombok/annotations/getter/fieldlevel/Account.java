package com.javabyexamples.java.tools.lombok.annotations.getter.fieldlevel;

import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
