package com.javabyexamples.spring.core.usingbean.beandependencies;

public class DefaultServiceOne implements ServiceOne {

    private final ServiceProperties serviceProperties;

    public DefaultServiceOne(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    public void one() {
        System.out.println("One.");
    }
}
