package com.javabyexamples.spring.core.beanpostprocessor;

import org.springframework.stereotype.Component;

@Component
public class FrenchGreetingService implements GreetingService {

    @Override
    public void hello() {
        System.out.println("Bonjour");
    }
}
