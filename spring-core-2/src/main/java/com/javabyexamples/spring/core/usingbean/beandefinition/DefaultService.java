package com.javabyexamples.spring.core.usingbean.beandefinition;

public class DefaultService implements ServiceOne, ServiceTwo {

    @Override
    public void one() {
        System.out.println("One");
    }

    @Override
    public void two() {
        System.out.println("Two.");
    }
}
