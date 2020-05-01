package com.javabyexamples.spring.core.scopeddependency.servicelocator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final TimerFactory timerFactory;

    @Autowired
    public ClientService(TimerFactory timerFactory) {
        this.timerFactory = timerFactory;
    }

    public void doWork() {
        final Timer timer = timerFactory.getTimer();
        timer.start();
        timer.stop();
    }
}
