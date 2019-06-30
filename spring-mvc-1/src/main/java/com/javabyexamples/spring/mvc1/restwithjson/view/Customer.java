package com.javabyexamples.spring.mvc1.restwithjson.view;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
@JsonView(PublicDetails.class)
public class Customer {

    private String name;

    private String lastName;

    @JsonView(PrivateDetails.class)
    private String country;

    @JsonView(PrivateDetails.class)
    private String creditCardNumber;
}
