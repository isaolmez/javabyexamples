package com.javabyexamples.spring.core.spel.rootobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String lastName;
    private boolean married;
    private Address address = new Address();
}
