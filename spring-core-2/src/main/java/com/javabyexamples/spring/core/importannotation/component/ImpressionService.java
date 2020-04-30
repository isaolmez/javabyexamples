package com.javabyexamples.spring.core.importannotation.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ImpressionService {

    private final Counter counter;

    public ImpressionService(Counter counter) {
        this.counter = counter;
    }

    public void countImpression() {
        counter.count();
    }

    @Bean
    public static Counter counter() {
        return new Counter();
    }
}
