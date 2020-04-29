package com.javabyexamples.spring.core.importconfiguration.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CounterManager {

    public void doWork() {
        // Do work.
    }

    @Bean
    public Counter counter() {
        return new Counter();
    }
}
