package com.javabyexamples.java.concurrency.composing.delegation.multiple.nonthreadsafe;

import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.common.annotation.InvariantCanBreak;
import com.javabyexamples.java.concurrency.common.annotation.NotThreadSafe;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.Statistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Delegating
@NotThreadSafe
public class NonThreadSafeStatistics implements Statistics {

    @Delegated
    private AtomicInteger current = new AtomicInteger(1);

    @Delegated
    private AtomicInteger previous = new AtomicInteger(0);

    private Lock checkLock = new ReentrantLock();

    @InvariantCanBreak
    @Override
    public void update() {
        current.incrementAndGet();
        previous.incrementAndGet();
    }

    @Override
    public boolean invariantHolds() {
        checkLock.lock();
        try {
            return current.get() - previous.get() == 1;
        } finally {
            checkLock.unlock();
        }
    }

    @Override
    public void print() {
        System.out.printf("Current: %s, Previous: %s%n", current.get(), previous.get());
    }
}
