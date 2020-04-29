package com.javabyexamples.spring.core.scopeddependency.servicelocator;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final ObjectFactory<Counter> counterProvider;

    public ClientService(ObjectProvider<Counter> counterProvider) {
        this.counterProvider = counterProvider;
    }

    public void doWork() {
        System.out.println(counterProvider.getObject().count());
    }
}
