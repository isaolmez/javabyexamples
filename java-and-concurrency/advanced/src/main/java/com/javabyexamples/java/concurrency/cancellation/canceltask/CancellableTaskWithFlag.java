package com.javabyexamples.java.concurrency.cancellation.canceltask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CancellableTaskWithFlag {

    public static void main(String[] args) throws InterruptedException {
        final CancellableTaskWithFlag cancellation = new CancellableTaskWithFlag();

        cancellation.startAndCancel();
    }

    public void startAndCancel() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final CancellableTask cancellableTask = new CancellableTask();
        executorService.execute(cancellableTask);

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        cancellableTask.cancel();

        executorService.shutdown();
    }

    public static class CancellableTask implements Runnable {

        private volatile boolean shouldStop;

        @Override
        public void run() {
            while (!shouldStop) {
                // Do work.
            }

            System.out.println("Stopping.");
        }

        public void cancel() {
            shouldStop = true;
        }
    }
}
