package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreUsage {

    public static void main(String[] args) {
        final SemaphoreUsage semaphoreUsage = new SemaphoreUsage();
        semaphoreUsage.semaphore();
    }

    public void semaphore() {
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

        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Customer());
        }

        threadPool.shutdown();
    }

    static class Restaurant {

        private final Semaphore semaphore = new Semaphore(3, true);
        private AtomicInteger totalCustomers = new AtomicInteger(0);

        public void eat() throws InterruptedException {
            semaphore.acquire();
            try {
                System.out.println("Total customers: " + totalCustomers.incrementAndGet());
                sleep(100);
            } finally {
                totalCustomers.decrementAndGet();
                semaphore.release();
            }
        }
    }
}
