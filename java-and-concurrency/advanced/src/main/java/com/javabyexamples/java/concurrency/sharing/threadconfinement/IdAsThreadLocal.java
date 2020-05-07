package com.javabyexamples.java.concurrency.sharing.threadconfinement;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class IdAsThreadLocal {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private static ThreadLocal<Integer> nextId = ThreadLocal.withInitial(() -> ID_GENERATOR.getAndIncrement());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> System.out.println(getId()));
        }

        ExecutorUtils.shutdownAndAwaitTermination(executorService);

        System.out.printf("Thread: %s%n", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(getId());
        }
    }

    // Shared global method
    private static int getId() {
        return nextId.get();
    }
}
