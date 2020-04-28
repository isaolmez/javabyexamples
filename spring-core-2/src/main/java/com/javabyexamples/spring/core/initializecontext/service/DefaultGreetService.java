package com.javabyexamples.spring.core.initializecontext.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultGreetService implements GreetService {

    @Override
    public void greet() {
        System.out.println("Greetings...");
    }
}
