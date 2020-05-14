package com.javabyexamples.java.concurrency.threadpool.shutdown;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShutdownExecutor {

    public static void main(String[] args) throws InterruptedException {
        final ShutdownExecutor shutdownExecutor = new ShutdownExecutor();

//        shutdownExecutor.shutdown();
//        shutdownExecutor.shutdownTheBlocker();
//        shutdownExecutor.shutdownTheLooper();
        shutdownExecutor.shutdownTheLooperRespectingInterrupt();

//        shutdownExecutor.shutdownNow();
//        shutdownExecutor.shutdownNowTheBlocker();
//        shutdownExecutor.shutdownNowTheLooper();
//        shutdownExecutor.shutdownNowTheLooperRespectingInterrupt();
//
//        shutdownExecutor.shutdownAndAwaitTermination();
    }

    //** shutdown

    public void shutdown() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                if (threadPool.isShutdown()) {
                    System.out.println("Pool is terminating.");
                }

                System.out.println("Doing work.");
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownTheBlocker() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        final BlockingQueue<String> values = new LinkedBlockingQueue<>();

        threadPool.submit(() -> {
            return values.take();
        });

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownTheLooper() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (true) {
                doWork();
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownTheLooperRespectingInterrupt() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                doWork();
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    //** shutdownNow

    public void shutdownNow() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                if (threadPool.isShutdown()) {
                    System.out.println("Pool is terminating.");
                }

                System.out.println("Doing work.");
            });
        }

        final List<Runnable> awaitingTasks = threadPool.shutdownNow();
        System.out.println("Tasks that didn't start: " + awaitingTasks.size());
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheBlocker() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        final BlockingQueue<String> values = new LinkedBlockingQueue<>();

        threadPool.submit(() -> {
            return values.take();
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheLooper() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (true) {
                doWork();
            }
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheLooperRespectingInterrupt() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                doWork();
            }
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    private void doWork() {
    }

    public void shutdownAndAwaitTermination() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(1);

        threadPool.shutdown(); // Disable new tasks from being submitted
        // Wait a while for existing tasks to terminate
        if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            threadPool.shutdownNow(); // Cancel currently executing tasks
            // Wait a while for tasks to respond to being cancelled
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Pool did not terminate");
            }
        }
    }

    private void printStatus(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
            System.out.printf("Is shutdown? : %s%n", threadPool.isShutdown());
            System.out.printf("Is terminating? : %s%n", threadPool.isTerminating());
            System.out.printf("Is terminated? : %s%n", threadPool.isTerminated());
            System.out.printf("Active task count: %s%n", threadPool.getActiveCount());
            System.out.printf("Completed task count: %s%n", threadPool.getCompletedTaskCount());
        }
    }
}
