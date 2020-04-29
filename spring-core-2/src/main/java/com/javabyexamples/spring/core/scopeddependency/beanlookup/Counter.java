package com.javabyexamples.spring.core.scopeddependency.beanlookup;

public class Counter {

    private int current = 0;

    public int count() {
        return current++;
    }
}
