package com.javabyexamples.spring.core.importannotation.metaannotation;

public class ImpressionService {

    private final Counter counter;

    public ImpressionService(Counter counter) {
        this.counter = counter;
    }

    public void countImpression() {
        counter.count();
    }
}
