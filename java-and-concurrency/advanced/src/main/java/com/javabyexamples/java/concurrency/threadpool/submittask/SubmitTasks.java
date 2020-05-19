package com.javabyexamples.java.concurrency.threadpool.submittask;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SubmitTasks {

    public static void main(String[] args) throws InterruptedException {
        final SubmitTasks submitTasks = new SubmitTasks();

//        submitTasks.submitRunnable();
        submitTasks.submitCallable();
//
//        submitTasks.execute();

//        submitTasks.invokeAll();
//        submitTasks.invokeAllWithTimeout();

//        submitTasks.invokeAny();
//        submitTasks.invokeAnyWithTimeout();

//        submitTasks.withCompletionService();

//        submitTasks.withRejectionHandler();
        submitTasks.withRejectedExecutionHandlerAsCallerRuns();
    }

    public void submitCallable() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Future<String> future1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hey!";
            }
        });

        final Future<String> future2 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if (LocalDateTime.now().getHour() < 12) {
                    throw new IOException("Planned checked exception.");
                }

                return "Hey!";
            }
        });

        final Future<String> future3 = executorService.submit(() -> {
            if (LocalDateTime.now().getHour() < 12) {
                throw new IOException("Planned checked exception.");
            }

            return "Hey!";
        });

        executorService.shutdown();
    }

    public void submitRunnable() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Future<?> future1 = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Working.");
            }
        });

        executorService.shutdown();
    }

    public void execute() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Working.");
            }
        });

        executorService.shutdown();
    }

    public void invokeAll() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final Callable<String> callable1 = new SleepAndReturn(900);
        final Callable<String> callable2 = new SleepAndReturn(700);
        final Callable<String> callable3 = new SleepAndReturn(300);
        final List<Callable<String>> callables = Arrays.asList(callable1, callable2, callable3);

        final List<Future<String>> futures = executorService.invokeAll(callables);

        for (Future<String> future : futures) {
            try {
                final String result = future.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        executorService.shutdown();
    }

    public void invokeAllWithTimeout() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final Callable<String> callable1 = new SleepAndReturn(900);
        final Callable<String> callable2 = new SleepAndReturn(700);
        final Callable<String> callable3 = new SleepAndReturn(300);
        final List<Callable<String>> callables = Arrays.asList(callable1, callable2, callable3);

        final List<Future<String>> futures = executorService.invokeAll(callables, 500, TimeUnit.MILLISECONDS);

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

        executorService.shutdown();
    }

    public void invokeAny() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final Callable<String> callable1 = new SleepAndReturn(900);
        final Callable<String> callable2 = new SleepAndReturn(700);
        final Callable<String> callable3 = new SleepAndReturn(300);
        final List<Callable<String>> callables = Arrays.asList(callable1, callable2, callable3);

        try {
            final String result = executorService.invokeAny(callables);

            System.out.println(result);
        } catch (ExecutionException e) {
            System.out.println("No tasks successfully completed.");
        }

        executorService.shutdown();
    }

    public void invokeAnyWithTimeout() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final Callable<String> callable1 = new SleepAndReturn(900);
        final Callable<String> callable2 = new SleepAndReturn(700);
        final Callable<String> callable3 = new SleepAndReturn(300);
        final List<Callable<String>> callables = Arrays.asList(callable1, callable2, callable3);

        try {
            final String result = executorService.invokeAny(callables, 500, TimeUnit.MILLISECONDS);

            System.out.println(result);
        } catch (TimeoutException e) {
            System.out.println("No successful result until timeout.");
        } catch (ExecutionException e) {
            System.out.println("No tasks successfully completed.");
        }

        executorService.shutdown();
    }

    public void withCompletionService() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(new SleepAndReturn(900));
        completionService.submit(new SleepAndReturn(700));
        completionService.submit(new SleepAndReturn(300));

        for (int i = 0; i < 3; i++) {
            final Future<String> future = completionService.take();
            try {
                final String result = future.get();

                System.out.println(result);
            } catch (ExecutionException e) {
                System.out.println("Error occurred.");
            }
        }

        executorService.shutdown();
    }

    public void withRejectedExecutionHandler() throws InterruptedException {
        final ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));

        executorService.submit(new SleepAndReturn(500));
        executorService.submit(new SleepAndReturn(500));
        executorService.submit(new SleepAndReturn(500));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    public void withRejectedExecutionHandlerAsCallerRuns() throws InterruptedException {
        final ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1), new CallerRunsPolicy());

        executorService.submit(new SleepAndReturn(500));
        executorService.submit(new SleepAndReturn(500));
        executorService.submit(new SleepAndReturn(500));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
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
