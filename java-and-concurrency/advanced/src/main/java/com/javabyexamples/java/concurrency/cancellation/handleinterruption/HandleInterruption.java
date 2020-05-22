package com.javabyexamples.java.concurrency.cancellation.handleinterruption;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HandleInterruption {

    public static void main(String[] args) {
        final HandleInterruption handleInterruption = new HandleInterruption();
        handleInterruption.interruptThread();
//        handleInterruption.tryToInterruptWhenThreadTerminated();

//        handleInterruption.checkInterruption();
//        handleInterruption.checkInterruptionByClearing();
//        handleInterruption.checkInterruptionFromAnother();

//        handleInterruption.handleAndThrowException();
//        handleInterruption.handleAndSetStatus();
//        handleInterruption.handleExceptionAndSetStatus();

    }

    public void interruptThread() {
        final Runnable task = () -> {
            int i = 0;
            while (i < Integer.MAX_VALUE) {
                i++;
            }
        };
        final Thread thread = new Thread(task);
        thread.start();

        System.out.println("Is thread interrupted: " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("Is thread interrupted: " + thread.isInterrupted());
    }

    public void tryToInterruptWhenThreadTerminated() throws InterruptedException {
        final Runnable task = () -> {
            System.out.println("Done.");
        };
        final Thread thread = new Thread(task);
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();
        System.out.println("Is thread interrupted: " + thread.isInterrupted());
    }

    public void checkInterruption() {
        new Thread(() -> {
            Thread.currentThread().interrupt();
            System.out.println("Is thread interrupted: " + Thread.currentThread().isInterrupted());
            System.out.println("Is thread interrupted: " + Thread.currentThread().isInterrupted());
        }).start();
    }

    public void checkInterruptionByClearing() {
        new Thread(() -> {
            Thread.currentThread().interrupt();
            System.out.println("Is thread interrupted: " + Thread.interrupted());
            System.out.println("Is thread interrupted: " + Thread.interrupted());
        }).start();
    }

    public void checkInterruptionFromAnother() {
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted.");
            }
        });

        thread.start();

        System.out.println("Is thread interrupted: " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("Is thread interrupted: " + thread.isInterrupted());
    }

    public void handleAndThrowException() throws InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Callable<Void> task = () -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("Interrupted, cleaning up and then throwing exception.");
                    throw new InterruptedException();
                }

                // Do work.
            }
        };

        final Future<?> future = executorService.submit(task);

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        final boolean cancel = future.cancel(true);
        System.out.println("Is cancelled?: " + cancel);

        executorService.shutdown();
    }

    public void handleAndSetStatus() throws InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Callable<String> task = () -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("Interrupted, cleaning up and then throwing exception.");
                    Thread.currentThread().interrupt();
                    return "Canceled";
                }

                // Do work.
            }
        };

        final Future<?> future = executorService.submit(task);

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        final boolean cancel = future.cancel(true);
        System.out.println("Is cancelled?: " + cancel);

        executorService.shutdown();
    }

    public void handleExceptionAndSetStatus() throws InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Callable<String> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted, cleaning up and then setting the interrupt status.");
                Thread.currentThread().interrupt();
                return "Canceled";
            }

            return "Ok";
        };

        final Future<String> future = executorService.submit(task);

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        future.cancel(true);

        executorService.shutdown();
    }
}
