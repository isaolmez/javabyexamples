package com.javabyexamples.spring.core.usingbean.lifecycle;

public class DefaultPersonService implements PersonService {

    public void init() {
        System.out.println("Initializing.");
    }

    @Override
    public void work() {
        System.out.println("Working.");
    }

    public void stop() {
        System.out.println("Stopping.");
    }
}
