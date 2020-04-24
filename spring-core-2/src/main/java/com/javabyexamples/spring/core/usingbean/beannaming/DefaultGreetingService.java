package com.javabyexamples.spring.core.usingbean.beannaming;

public class DefaultGreetingService implements GreetingService {

    @Override
    public void greet() {
        System.out.println("Hi");
    }
}
