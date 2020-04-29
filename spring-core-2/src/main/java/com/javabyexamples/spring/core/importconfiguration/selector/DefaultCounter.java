package com.javabyexamples.spring.core.importconfiguration.selector;

public class DefaultCounter implements Counter {

    private int current = 0;

    @Override
    public void count() {
        System.out.println(current++);
    }
}
