package com.javabyexamples.spring.core.usingbean.beandependencies;

public class DefaultServiceTwo implements ServiceTwo {

    private final ServiceProperties serviceProperties;

    public DefaultServiceTwo(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    public void two() {
        System.out.println("Two.");
    }
}
