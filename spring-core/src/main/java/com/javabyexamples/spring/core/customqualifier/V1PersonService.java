package com.javabyexamples.spring.core.customqualifier;

import org.springframework.stereotype.Component;

@Component
@Version1
public class V1PersonService implements PersonService {

    @Override
    public void hello() {
        System.out.println("Hello from: " + getClass().getSimpleName());
    }
}
