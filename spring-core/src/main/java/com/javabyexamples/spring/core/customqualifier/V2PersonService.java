package com.javabyexamples.spring.core.customqualifier;

import org.springframework.stereotype.Component;

@Component
@Version2
public class V2PersonService implements PersonService {

    @Override
    public void hello() {
        System.out.println("Hello from: " + getClass().getSimpleName());
    }
}
