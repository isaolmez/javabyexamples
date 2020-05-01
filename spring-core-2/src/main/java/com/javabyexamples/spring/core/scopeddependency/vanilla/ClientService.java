package com.javabyexamples.spring.core.scopeddependency.vanilla;

import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final Timer timer;

    public ClientService(Timer timer) {
        this.timer = timer;
    }

    public void doWork() {
        timer.start();
        System.out.println("Doing work...");
        timer.stop();
    }
}
