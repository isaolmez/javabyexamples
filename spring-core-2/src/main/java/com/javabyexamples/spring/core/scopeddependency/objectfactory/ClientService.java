package com.javabyexamples.spring.core.scopeddependency.objectfactory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final ObjectFactory<Timer> timerObjectFactory;

    public ClientService(ObjectProvider<Timer> timerObjectFactory) {
        this.timerObjectFactory = timerObjectFactory;
    }

    public void doWork() {
        final Timer timer = timerObjectFactory.getObject();
        timer.start();
        timer.stop();
    }
}
