package com.javabyexamples.java.concurrency.advanced.threadsafety.visibility.longdouble;

import com.javabyexamples.java.concurrency.advanced.common.annotation.NotThreadSafe;

@NotThreadSafe
public class LongDoubleContainer {

    public long counter = 0;

    public void bounce() {
        if (counter == 0) {
            counter = Long.MAX_VALUE;
        } else {
            counter = 0;
        }
    }
}
