package com.javabyexamples.spring.mvc1.restwithjson;

import lombok.Data;

@Data
public class Customer {

    private String name;
    private String lastName;
    private String country;
    private String creditCardNumber;
}
