package com.javabyexamples.spring.core.closecontext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MonitorService {

    @PostConstruct
    public void init() {
        System.out.println("Initialized...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying...");
    }
}
