package com.javabyexamples.java.concurrency.cancellation.canceltask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancelTaskWithFlag {

    private volatile boolean shouldStop = false;

    public static void main(String[] args) throws InterruptedException {
        final CancelTaskWithFlag cancellation = new CancelTaskWithFlag();

        cancellation.startAndCancelAll();
    }

    public void startAndCancelAll() throws InterruptedException {
        final int threadCount = 5;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final Runnable task = () -> {
                while (!shouldStop) {
                    // Do work.
                }

                System.out.println("Stopping.");
            };
            executorService.execute(task);
        }

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        cancelAll();

        executorService.shutdown();
    }

    public void cancelAll() {
        shouldStop = true;
    }
}
