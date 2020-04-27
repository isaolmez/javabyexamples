package com.javabyexamples.spring.core.initializeandclose.initialize;

import org.springframework.stereotype.Component;

@Component
public class DefaultGreetService implements GreetService {

    @Override
    public void greet() {
        System.out.println("Greetings...");
    }
}
