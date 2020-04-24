package com.javabyexamples.spring.core.jsr330;

import javax.inject.Named;

@Named
public class JsrPersonRepository {

    public void list() {
        System.out.println("Listing person");
    }
}
