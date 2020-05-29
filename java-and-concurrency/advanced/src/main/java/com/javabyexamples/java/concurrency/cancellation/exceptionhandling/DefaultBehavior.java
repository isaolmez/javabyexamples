package com.javabyexamples.java.concurrency.cancellation.exceptionhandling;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DefaultBehavior {

    public static void main(String[] args) {
        final DefaultBehavior defaultBehavior = new DefaultBehavior();

        defaultBehavior.executeThenThrowUnchecked();
//        defaultBehavior.submitThenThrowUnchecked();
//        defaultBehavior.submitThenThrowUncheckedThenGet();
    }

    public void executeThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });

        executorService.shutdown();
    }

    public void submitThenThrowUnchecked() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        final Future<Object> futureHandle = executorService.submit(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after submit()");
        });

        executorService.shutdown();
    }

    public void submitThenThrowUncheckedThenGet() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Future<Object> future = executorService.submit(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after submit()");
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
