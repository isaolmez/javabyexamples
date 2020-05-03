package com.javabyexamples.java.concurrency.future;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;

public class ThreadTest {

    public static void main(String[] args) {
        ConcurrencyUtils.runStaticMethods(ThreadTest.class, 1000);
    }

    public static void join() throws InterruptedException {
        Thread thread = new Thread(() -> ConcurrencyUtils.sleep(3000));
        thread.start();
        System.out.println("Thread started");
        thread.join();
        System.out.println("Joined with spawned thread");
    }

    public static void sleepZero() {
        System.out.printf("Sleeping at: %s for %s %n", System.currentTimeMillis(), 0);
        ConcurrencyUtils.sleep(0);
        System.out.printf("Woke up at: %s%n", System.currentTimeMillis());
    }

    public static void sleep() {
        System.out.printf("Sleeping at: %s for %s %n", System.currentTimeMillis(), 1);
        ConcurrencyUtils.sleep(1);
        System.out.printf("Woke up at: %s%n", System.currentTimeMillis());
    }
}
