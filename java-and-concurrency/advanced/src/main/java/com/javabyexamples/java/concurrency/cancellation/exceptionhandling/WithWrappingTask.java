package com.javabyexamples.java.concurrency.cancellation.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WithWrappingTask {

    public static void main(String[] args) throws InterruptedException {
        final WithWrappingTask withWrappingTask = new WithWrappingTask();
        withWrappingTask.executeThenThrowUnchecked();
    }

    public void executeThenThrowUnchecked() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final CatchingRunnable catchingRunnable = new CatchingRunnable(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });
        executorService.execute(catchingRunnable);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    public static class CatchingRunnable implements Runnable {

        private final Runnable runnable;

        public CatchingRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }
}
