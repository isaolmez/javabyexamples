package com.javabyexamples.java.concurrency.threadpool.shutdown;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShutdownExecutor {

    public static void main(String[] args) throws InterruptedException {
        final ShutdownExecutor shutdownExecutor = new ShutdownExecutor();

//        shutdownExecutor.shutdownTheSleeperWithoutAwait();

//        shutdownExecutor.shutdownTheSleeper();
//        shutdownExecutor.shutdownNowTheSleeper();

//        shutdownExecutor.shutdownNowTheLooper();
//        shutdownExecutor.shutdownNowTheLooperRespectingInterrupt();

        shutdownExecutor.shutdownNowWhenThereAreTasksInQueue();
    }

    public void shutdownTheSleeperWithoutAwait() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);

        threadPool.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        });

        threadPool.shutdown();

        printStatus(threadPool);
    }

    public void shutdownTheSleeper() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);

        threadPool.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheSleeper() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheLooper() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (true) {
                doSomething();
            }
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheLooperRespectingInterrupt() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
            }
        });

        threadPool.shutdownNow();
        threadPool.awaitTermination(2, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowWhenThereAreTasksInQueue() throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> doSomething());
        }

        final List<Runnable> notStartedTasks = threadPool.shutdownNow();
        System.out.println("The count of not started tasks: " + notStartedTasks.size());
        threadPool.awaitTermination(1, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    private void doSomething() {
    }

    private void doSomethingInterruptibly() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
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
