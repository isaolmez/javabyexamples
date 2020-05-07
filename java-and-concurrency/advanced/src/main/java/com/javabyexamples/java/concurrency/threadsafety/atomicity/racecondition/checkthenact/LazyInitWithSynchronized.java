package com.javabyexamples.java.concurrency.threadsafety.atomicity.racecondition.checkthenact;

import com.javabyexamples.java.concurrency.common.annotation.NotThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class LazyInitWithSynchronized {

    private static final AtomicInteger initializationCount = new AtomicInteger(0);

    private static Object instance;

    public static synchronized Object get() {
        if (instance == null) {
            instance = new Object();
            initializationCount.incrementAndGet();
        }

        return instance;
    }

    public static int getInitializationCount() {
        return initializationCount.get();
    }
}
