package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        lock();
        tryLock();
    }

    public static void lock() throws InterruptedException {
        final SharedCounter sharedCounter = new SharedCounter();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        class Caller implements Runnable {

            @Override
            public void run() {
                System.out.printf("Result: %s%n", sharedCounter.increment());
            }
        }

        threadPool.execute(new Caller());
        threadPool.execute(new Caller());
        threadPool.execute(new Caller());
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static void tryLock() throws InterruptedException {
        final SharedCounter sharedCounter = new SharedCounter();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        class Caller implements Runnable {

            @Override
            public void run() {
                System.out.printf("Result: %s%n", sharedCounter.maybeIncrement());
            }
        }

        threadPool.execute(new Caller());
        threadPool.execute(new Caller());
        threadPool.execute(new Caller());
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static class SharedCounter {

        private int value = 0;
        private Lock lock = new ReentrantLock();

        public int increment() {
            System.out.printf("%s is attempting to lock%n", Thread.currentThread().getName());
            lock.lock();
            try {
                System.out.printf("%s has acquired lock%n", Thread.currentThread().getName());
                sleep(100);
                return ++value;
            } finally {
                System.out.printf("%s is unlocking%n", Thread.currentThread().getName());
                lock.unlock();
            }
        }

        public int maybeIncrement() {
            System.out.printf("%s is attempting to lock%n", Thread.currentThread().getName());
            if (lock.tryLock()) {
                try {
                    System.out.printf("%s has acquired lock%n", Thread.currentThread().getName());
                    sleep(100);
                    return ++value;
                } finally {
                    System.out.printf("%s is unlocking%n", Thread.currentThread().getName());
                    lock.unlock();
                }
            } else {
                System.out.printf("%s could not acquire lock, passing%n", Thread.currentThread().getName());
                return value;
            }
        }
    }
}
