package com.javabyexamples.spring.core.scopeddependency.vanilla;

import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final Counter counter;

    public ClientService(Counter counter) {
        this.counter = counter;
    }

    public void doWork() {
        System.out.println(counter.count());
    }
}
