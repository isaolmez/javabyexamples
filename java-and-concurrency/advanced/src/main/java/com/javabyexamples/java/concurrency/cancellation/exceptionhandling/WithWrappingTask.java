package com.javabyexamples.java.concurrency.cancellation.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithWrappingTask {

    public static void main(String[] args) {
        final WithWrappingTask withWrappingTask = new WithWrappingTask();
        withWrappingTask.executeThenThrowUnchecked();
    }

    public void executeThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final CatchingRunnable catchingRunnable = new CatchingRunnable(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });
        executorService.execute(catchingRunnable);

        executorService.shutdown();
    }

    public static class CatchingRunnable implements Runnable {

        private final Runnable delegate;

        public CatchingRunnable(Runnable delegate) {
            this.delegate = delegate;
        }

        @Override
        public void run() {
            try {
                delegate.run();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()); // Log, notify etc...
                throw e;
            }
        }
    }
}
