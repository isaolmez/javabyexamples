package com.javabyexamples.spring.core.beanfactorypostprocessor;

import org.springframework.stereotype.Component;

@Component("greetingBean")
public class GreetingBean {

    public void sayHello() {
        System.out.println("Hello!");
    }
}
