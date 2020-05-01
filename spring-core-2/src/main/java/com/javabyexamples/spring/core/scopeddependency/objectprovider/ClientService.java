package com.javabyexamples.spring.core.scopeddependency.objectprovider;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final ObjectProvider<Timer> timerObjectProvider;

    public ClientService(ObjectProvider<Timer> timerObjectProvider) {
        this.timerObjectProvider = timerObjectProvider;
    }

    public void doWork() {
        final Timer timer = timerObjectProvider.getIfAvailable();
        timer.start();
        timer.stop();
    }
}
