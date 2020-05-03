package com.javabyexamples.java.concurrency.advanced.composing.delegation.multiple.threadsafe;

import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.advanced.common.annotation.InvariantHolds;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.advanced.composing.delegation.multiple.Statistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Delegating
@ThreadSafe
public class ThreadSafeStatistics implements Statistics {

    @Delegated
    private AtomicInteger current = new AtomicInteger(1);

    @Delegated
    private AtomicInteger previous = new AtomicInteger(0);

    private Lock lock = new ReentrantLock();

    @InvariantHolds
    @Override
    public void update() {
        lock.lock();
        try {
            current.incrementAndGet();
            previous.incrementAndGet();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean invariantHolds() {
        lock.lock();
        try {
            return current.get() - previous.get() == 1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void print() {
        System.out.printf("Current: %s, Previous: %s%n", current.get(), previous.get());
    }
}
