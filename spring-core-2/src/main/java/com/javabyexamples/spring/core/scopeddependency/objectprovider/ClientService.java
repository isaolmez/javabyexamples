package com.javabyexamples.spring.core.scopeddependency.objectprovider;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final ObjectProvider<Counter> counterProvider;

    public ClientService(
      ObjectProvider<Counter> counterProvider) {
        this.counterProvider = counterProvider;
    }

    public void doWork() {
        System.out.println(counterProvider.getIfAvailable().count());
    }
}
