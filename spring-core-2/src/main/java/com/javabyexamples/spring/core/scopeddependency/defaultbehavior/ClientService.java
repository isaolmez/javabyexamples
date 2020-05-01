package com.javabyexamples.spring.core.scopeddependency.defaultbehavior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final Timer timer;

    @Autowired
    public ClientService(Timer timer) {
        this.timer = timer;
    }

    public void doWork() {
        timer.start();
        timer.stop();
    }
}
