package com.javabyexamples.spring.core.scopeddependency.defaultbehavior;

import org.springframework.stereotype.Component;

@Component
public class AnotherClientService {

    private final Timer timer;

    public AnotherClientService(Timer timer) {
        this.timer = timer;
    }

    public void doWork() {
        timer.start();
        timer.stop();
    }
}
