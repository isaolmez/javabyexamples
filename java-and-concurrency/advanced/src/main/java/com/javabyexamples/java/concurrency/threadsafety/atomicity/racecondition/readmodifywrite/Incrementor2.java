package com.javabyexamples.java.concurrency.threadsafety.atomicity.racecondition.readmodifywrite;

import com.javabyexamples.java.concurrency.common.annotation.NotThreadSafe;

@NotThreadSafe
public class Incrementor2 implements Incrementor {

    private int counter = 0;

    @Override
    public void increment() {
        counter = counter + 1;
    }

    @Override
    public void reset() {
        counter = 0;
    }

    @Override
    public int get() {
        return counter;
    }
}
