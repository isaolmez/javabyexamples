package com.javabyexamples.spring.core.scopeddependency.provider;

import javax.inject.Provider;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final Provider<Counter> counterProvider;

    public ClientService(Provider<Counter> counterProvider) {
        this.counterProvider = counterProvider;
    }

    public void doWork() {
        System.out.println(counterProvider.get().count());
    }
}
