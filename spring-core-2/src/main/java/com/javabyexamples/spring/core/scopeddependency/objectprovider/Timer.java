package com.javabyexamples.spring.core.scopeddependency.objectprovider;

import java.util.UUID;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Timer {

    private final String id = UUID.randomUUID().toString();
    private long start;

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        long elapsed = System.nanoTime() - start;
        System.out.println(id + ": " + elapsed);
    }
}
