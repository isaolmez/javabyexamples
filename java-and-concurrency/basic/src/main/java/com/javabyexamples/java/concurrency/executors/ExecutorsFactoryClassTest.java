package com.javabyexamples.java.concurrency.executors;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorsFactoryClassTest {

    public static void main(String[] args) {
        ConcurrencyUtils.runStaticMethods(ExecutorsFactoryClassTest.class, 1000);
    }

    public static void singleThreaded() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.printf("Thread pool details: %s%n", executorService);
    }

    public static void fixedSize() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.printf("Thread pool details: %s%n", executorService);
    }

    public static void cached() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.printf("Thread pool details: %s%n", executorService);
    }

    public static void scheduled() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        System.out.printf("Thread pool details: %s%n", scheduledExecutorService);
    }
}
