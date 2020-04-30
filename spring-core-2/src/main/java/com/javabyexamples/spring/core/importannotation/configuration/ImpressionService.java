package com.javabyexamples.spring.core.importannotation.configuration;

public class ImpressionService {

    private final Counter counter;

    public ImpressionService(Counter counter) {
        this.counter = counter;
    }

    public void countImpression(){
        counter.count();
    }
}
