package com.javabyexamples.java.concurrency.threadsafety.atomicity.racecondition.readmodifywrite;

import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@Delegating
@ThreadSafe
public class Incrementor4 implements Incrementor {

    @Delegated
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public void reset() {
        counter.set(0);
    }

    @Override
    public int get() {
        return counter.get();
    }
}
