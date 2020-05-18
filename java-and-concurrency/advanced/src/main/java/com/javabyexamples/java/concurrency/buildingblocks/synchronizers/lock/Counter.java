package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        final Counter counter = new Counter();

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> counter.increment());

            executorService.submit(() -> counter.decrement());
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(counter.count);
    }

    private final Lock lock = new ReentrantLock();
    private final Condition notZero = lock.newCondition();
    private final Condition notAtLimit = lock.newCondition();

    private final int limit = 50;
    private int count = 0;

    public int increment() throws InterruptedException {
        lock.lock();
        try {
            while (count == limit) {
                notAtLimit.await();
            }

            count++;
            notZero.signalAll();

            return count;
        } finally {
            lock.unlock();
        }
    }

    public int decrement() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notZero.await();
            }

            count--;
            notAtLimit.signal();
            return count;
        } finally {
            lock.unlock();
        }
    }

    public int incrementBy(int amount) throws InterruptedException {
        lock.lock();
        try {
            while (count == limit) {
                notAtLimit.await();
            }

            count = count + amount;
            notZero.signal();

            return count;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
