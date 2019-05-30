package com.javabyexamples.spring.core.jsr330;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    public void list() {
        System.out.println("Listing person");
    }
}
