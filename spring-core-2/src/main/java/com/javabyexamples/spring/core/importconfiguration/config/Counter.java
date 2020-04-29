package com.javabyexamples.spring.core.importconfiguration.config;

public class Counter {

    private int current = 0;

    public void count() {
        System.out.println(current++);
    }
}
