package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        semaphore();
    }

    public static void semaphore() throws InterruptedException {
        final Restaurant restaurant = new Restaurant();
        final ExecutorService threadPool = Executors.newFixedThreadPool(3);
        class Customer implements Runnable {

            @Override
            public void run() {
                try {
                    restaurant.eat();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        threadPool.execute(new Customer());
        threadPool.execute(new Customer());
        threadPool.execute(new Customer());
        threadPool.execute(new Customer());
        threadPool.execute(new Customer());
        threadPool.execute(new Customer());
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    static class Restaurant {

        private final Semaphore semaphore = new Semaphore(3, true);
        private volatile AtomicInteger current = new AtomicInteger(0);

        public void eat() throws InterruptedException {
            semaphore.acquire();
            try {
                System.out.printf("%s - Current: %s%n", Thread.currentThread().getName(), current.incrementAndGet());
                sleep(100);
            } finally {
                System.out.printf("%s - Will be: %s%n", Thread.currentThread().getName(), current.decrementAndGet());
                semaphore.release();
            }
        }
    }
}
