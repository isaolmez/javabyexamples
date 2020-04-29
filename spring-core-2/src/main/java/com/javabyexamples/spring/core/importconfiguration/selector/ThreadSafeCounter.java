package com.javabyexamples.spring.core.importconfiguration.selector;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter implements Counter {

    private final AtomicInteger current = new AtomicInteger();

    @Override
    public void count() {
        System.out.println(current.getAndIncrement());
    }
}
