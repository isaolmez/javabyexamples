package com.javabyexamples.java.concurrency.advanced.composing.delegation.multiple.threadsafe;

import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.advanced.common.annotation.InvariantHolds;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.advanced.composing.delegation.multiple.Statistics;
import java.util.concurrent.atomic.AtomicInteger;

@Delegating
@ThreadSafe
public class MonitorThreadSafeStatistics implements Statistics {

    @Delegated
    private AtomicInteger current = new AtomicInteger(1);

    @Delegated
    private AtomicInteger previous = new AtomicInteger(0);

    @InvariantHolds
    @Override
    public synchronized void update() {
        current.incrementAndGet();
        previous.incrementAndGet();
    }

    @Override
    public synchronized boolean invariantHolds() {
        return current.get() - previous.get() == 1;
    }

    @Override
    public void print() {
        System.out.printf("Current: %s, Previous: %s%n", current.get(), previous.get());
    }
}
