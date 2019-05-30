package com.javabyexamples.spring.core.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class WithCustomMethodsService {

    public void initialize() throws Exception {
        System.out.println("Initializing " + getClass().getSimpleName());
    }

    public void destroy() {
        System.out.println("Destroying " + getClass().getSimpleName());
    }
}
