package com.javabyexamples.java.concurrency.cancellation.canceltask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancelTaskInPoolWithShutdownNow {

    public static void main(String[] args) throws InterruptedException {
        final CancelTaskInPoolWithShutdownNow cancellation = new CancelTaskInPoolWithShutdownNow();

        cancellation.startAndCancel();
    }

    public void startAndCancel() throws InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Do work.
            }

            System.out.println("Stopping.");
        };
        executorService.submit(task);

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        executorService.shutdownNow();
    }
}
