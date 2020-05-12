package com.javabyexamples.java.concurrency.executors;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorShutdownDetails {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorShutdownDetails executorShutdownDetails = new ExecutorShutdownDetails();
        executorShutdownDetails.shutdownTheSleeper();
    }

    public void shutdownTheSleeper() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        threadPool.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.SECONDS);

        printStatus(threadPool);
    }

    public void shutdownNowTheSleeper() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> ConcurrencyUtils.sleep(10000));

        System.out.println("Shutting down now");
        threadPool.shutdownNow();
        printStatus(threadPool);
    }

    public void shutdownNowTheLooper() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> {
            while (true) {
                doSomething();
            }
        });

        System.out.println("Shutting down now");
        threadPool.shutdownNow();
        printStatus(threadPool);
    }

    public void shutdownNowTheLooperRespectingInterrupt() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
            }
        });

        System.out.println("Shutting down now");
        threadPool.shutdownNow();
        printStatus(threadPool);
    }

    public void shutdownTheSleeperAndAwaitTermination() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> ConcurrencyUtils.sleep(10000));

        System.out.println("Shutting down");
        threadPool.shutdown();
        threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        printStatus(threadPool);
    }

    public void shutdownNowTheSleeperAndAwaitTermination() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> ConcurrencyUtils.sleep(10000));

        System.out.println("Shutting down now");
        threadPool.shutdownNow();
        threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        printStatus(threadPool);
    }

    public void shutdownNowTheLooperAndAwaitTermination() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> {
            while (true) {
                doSomething();
            }
        });

        System.out.println("Shutting down now");
        threadPool.shutdownNow();
        threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        printStatus(threadPool);
    }

    private static void doSomething() {
    }

    private static void printStatus(ThreadPoolExecutor threadPool) {
        System.out.printf("Is shutdown? : %s%n", threadPool.isShutdown());
        System.out.printf("Is terminating? : %s%n", threadPool.isTerminating());
        System.out.printf("Is terminated? : %s%n", threadPool.isTerminated());
        System.out.printf("Active task count: %s%n", threadPool.getActiveCount());
        System.out.printf("Completed task count: %s%n", threadPool.getCompletedTaskCount());
    }
}
