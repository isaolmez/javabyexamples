package com.javabyexamples.java.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadInterruptUsage {

    public static void main(String[] args) {
        final ThreadInterruptUsage threadInterruptUsage = new ThreadInterruptUsage();
        threadInterruptUsage.interrupt();
    }

    public void interrupt() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().hashCode());

            // Do not interrupt the thread you don't own.
            System.out.println("Interrupting the thread.");
            Thread.currentThread().interrupt();
            System.out.println("Is interrupted: " + Thread.currentThread().isInterrupted());
        });

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().hashCode());

            System.out.println("Is interrupted: " + Thread.currentThread().isInterrupted());
        });

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().hashCode());
        });

        executorService.shutdown();
    }
}
