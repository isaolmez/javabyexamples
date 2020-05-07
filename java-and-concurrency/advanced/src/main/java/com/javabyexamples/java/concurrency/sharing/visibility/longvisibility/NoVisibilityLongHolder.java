package com.javabyexamples.java.concurrency.sharing.visibility.longvisibility;

import com.javabyexamples.java.concurrency.common.annotation.NotThreadSafe;

@NotThreadSafe
public class NoVisibilityLongHolder implements LongHolder {

    private long counter = 0;

    public void alternate() {
        if (counter == 0) {
            counter = Long.MAX_VALUE;
        } else {
            counter = 0;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
