package com.javabyexamples.spring.core.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class CustomMethodsService {

    public void initialize() {
        System.out.println("Initializing " + getClass().getSimpleName());
    }

    public void destroy() {
        System.out.println("Destroying " + getClass().getSimpleName());
    }
}
