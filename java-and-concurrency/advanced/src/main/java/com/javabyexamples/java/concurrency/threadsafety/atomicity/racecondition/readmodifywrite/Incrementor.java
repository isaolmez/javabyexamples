package com.javabyexamples.java.concurrency.threadsafety.atomicity.racecondition.readmodifywrite;

public interface Incrementor {

    void increment();

    void reset();

    int get();
}
