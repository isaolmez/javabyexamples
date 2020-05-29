package com.javabyexamples.java.concurrency.cancellation.exceptionhandling;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class WithUncaughtExceptionHandler {

    public static void main(String[] args) {
        final WithUncaughtExceptionHandler exceptionHandler = new WithUncaughtExceptionHandler();
        exceptionHandler.executeThenThrowUnchecked();
//        exceptionHandler.submitThenThrowUnchecked();
    }

    public void executeThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1, new AppThreadFactory());
        executorService.execute(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });

        executorService.shutdown();
    }

    public void submitThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1, new AppThreadFactory());
        executorService.submit(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });

        executorService.shutdown();
    }

    public static class AppThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            final Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler(new AppExceptionHandler());
            return thread;
        }
    }

    public static class AppExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Uncaught Exception occurred on thread: " + t.getName());
            System.out.println("Exception message: " + e.getMessage());
        }
    }

}
