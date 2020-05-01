package com.javabyexamples.spring.core.scopeddependency.servicelocator;

import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final TimerFactory timerFactory;

    public ClientService(TimerFactory timerFactory) {
        this.timerFactory = timerFactory;
    }

    public void doWork() {
        final Timer timer = timerFactory.getTimer();
        timer.start();
        timer.stop();
    }
}
