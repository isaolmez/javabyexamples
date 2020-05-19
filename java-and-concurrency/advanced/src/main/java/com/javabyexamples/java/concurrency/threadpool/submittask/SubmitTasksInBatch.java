package com.javabyexamples.java.concurrency.threadpool.submittask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubmitTasksInBatch {

    private final int threadCount = 5;

    public static void main(String[] args) throws InterruptedException {
        final Callable<String> task1 = new SleepAndReturn(900);
        final Callable<String> task2 = new SleepAndReturn(700);
        final Callable<String> task3 = new SleepAndReturn(300);
        final List<Callable<String>> tasks = Arrays.asList(task1, task2, task3);

        final SubmitTasksInBatch submitTasks = new SubmitTasksInBatch();

        submitTasks.batchWithInvokeAll(tasks);
//        submitTasks.batchWithInvokeAllWithTimeout(tasks);
//
//        submitTasks.batchwithInvokeAny(tasks);
//        submitTasks.batchWithInvokeAnyWithTimeout(tasks);
//
//        submitTasks.batchWithCompletionService(tasks);
//
//        submitTasks.submitInBatchManually(tasks);
    }

    public void submitInBatchManually(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final List<Future<String>> futures = new ArrayList<>();
        for (Callable<String> task : tasks) {
            futures.add(threadPool.submit(task));
        }

        for (Future<String> future : futures) {
            try {
                final String result = future.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        threadPool.shutdown();
    }

    public void batchWithInvokeAll(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final List<Future<String>> futures = threadPool.invokeAll(tasks);

        for (Future<String> future : futures) {
            try {
                final String result = future.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        threadPool.shutdown();
    }

    public void batchWithInvokeAllWithTimeout(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final List<Future<String>> futures = threadPool.invokeAll(tasks, 500, TimeUnit.MILLISECONDS);

        for (Future<String> future : futures) {
            try {
                final String result = future.get();
                System.out.println(result);
            } catch (CancellationException e) {
                System.out.println("Cancelled.");
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        threadPool.shutdown();
    }

    public void batchwithInvokeAny(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        try {
            final String result = threadPool.invokeAny(tasks);

            System.out.println(result);
        } catch (ExecutionException e) {
            System.out.println("No tasks successfully completed.");
        }

        threadPool.shutdown();
    }

    public void batchWithInvokeAnyWithTimeout(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        try {
            final String result = threadPool.invokeAny(tasks, 200, TimeUnit.MILLISECONDS);

            System.out.println(result);
        } catch (TimeoutException e) {
            System.out.println("No successful result until timeout.");
        } catch (ExecutionException e) {
            System.out.println("No tasks successfully completed.");
        }

        threadPool.shutdown();
    }

    public void batchWithCompletionService(List<Callable<String>> tasks) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(threadPool);

        for (Callable<String> task : tasks) {
            completionService.submit(task);
        }

        for (int i = 0; i < tasks.size(); i++) {
            final Future<String> future = completionService.take();
            try {
                final String result = future.get();

                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        threadPool.shutdown();
    }

    public static class SleepAndReturn implements Callable<String> {

        private final int millis;

        public SleepAndReturn(int millis) {
            this.millis = millis;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(millis);

            return "Done at " + millis;
        }
    }
}
