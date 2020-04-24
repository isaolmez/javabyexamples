package com.javabyexamples.spring.core.usingbean.lifecycle;

public class DefaultServiceOne implements ServiceOne {

    public void init() {
        System.out.println("Initializing.");
    }

    @Override
    public void perform() {
        System.out.println("Performing.");
    }

    public void stop() {
        System.out.println("Stopping.");
    }
}
