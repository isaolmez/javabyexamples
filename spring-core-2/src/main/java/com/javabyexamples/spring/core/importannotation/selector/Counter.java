package com.javabyexamples.spring.core.importannotation.selector;

public class Counter {

    private final String environment;
    private int current = 0;

    public Counter(String environment) {
        this.environment = environment;
    }

    public void count() {
        System.out.println(environment + ": " + current++);
    }
}
