package com.javabyexamples.spring.core.importconfiguration.selector;

public class ImpressionService {

    private final Counter counter;

    public ImpressionService(Counter counter) {
        this.counter = counter;
    }

    public void perform() {
        counter.count();
    }
}
