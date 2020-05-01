package com.javabyexamples.spring.core.scopeddependency.beanlookup;

import java.util.UUID;

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
