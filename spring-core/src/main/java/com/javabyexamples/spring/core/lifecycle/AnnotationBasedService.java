package com.javabyexamples.spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedService {

    @PostConstruct
    public void init() {
        System.out.println("Initializing " + getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying " + getClass().getSimpleName());
    }
}
