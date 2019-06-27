package com.javabyexamples.libraries.xsteam;

import lombok.Data;

@Data
public class Employee {

    private String name;
    private String lastname;
    private PhoneNumber personal;
    private PhoneNumber work;
}
