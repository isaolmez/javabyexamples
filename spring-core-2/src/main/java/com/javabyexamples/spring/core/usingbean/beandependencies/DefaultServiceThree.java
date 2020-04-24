package com.javabyexamples.spring.core.usingbean.beandependencies;

public class DefaultServiceThree implements ServiceThree {

    private final ServiceProperties serviceProperties;

    public DefaultServiceThree(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    public void three() {
        System.out.println("Three.");
    }
}
