package com.javabyexamples.java.concurrency.cancellation.canceltask;

import java.util.concurrent.TimeUnit;

public class CancelTaskWithInterruption {

    public static void main(String[] args) throws InterruptedException {
        final CancelTaskWithInterruption cancellation = new CancelTaskWithInterruption();

        cancellation.startAndCancel();
    }

    public void startAndCancel() throws InterruptedException {
        final Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Do work.
            }

            System.out.println("Stopping.");
        };

        final Thread thread = new Thread(task);
        thread.start();

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        thread.interrupt();
    }
}
