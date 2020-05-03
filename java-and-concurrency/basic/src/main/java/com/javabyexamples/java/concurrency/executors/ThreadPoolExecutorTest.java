package com.javabyexamples.java.concurrency.executors;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreadPoolExecutorTest {

    private static final int CORE_SIZE = 3;
    private static final int MAX_SIZE = 6;

    public static void main(String[] args) {
        ConcurrencyUtils.runStaticMethods(ThreadPoolExecutorTest.class, 3000);
    }

    public static void executeOne() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        threadPool.execute(ConcurrencyUtils.getRunnable(1));
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void executeEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        IntStream.range(1, CORE_SIZE + 1).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order)));
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void executeGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        IntStream.range(1, MAX_SIZE * 2).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order)));
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void preStartCoreThreads() {
        ThreadPoolExecutor threadPool = newThreadPool();
        threadPool.prestartAllCoreThreads();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void rejectTask() {
        ThreadPoolExecutor threadPool = newSingleThreadedPoolWithBoundedTaskQueue();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        IntStream.range(1, 5).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order, 1000)));
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void submitOne() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        Future<String> result = threadPool.submit(ConcurrencyUtils.getCallable(1));
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());

        try {
            System.out.printf("Result: %s%n", result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void submitEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        List<Future<String>> results = Stream.of(1, 2, 3)
          .map(order -> threadPool.submit(ConcurrencyUtils.getCallable(order)))
          .collect(Collectors.toList());
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());

        results.forEach(future -> {
            try {
                System.out.printf("Result: %s%n", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public static void submitGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
        List<Future<String>> results = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
          .map(order -> threadPool.submit(ConcurrencyUtils.getCallable(order)))
          .collect(Collectors.toList());
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());

        results.forEach(future -> {
            try {
                System.out.printf("Result: %s%n", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    private static ThreadPoolExecutor newThreadPool() {
        return new ThreadPoolExecutor(3, 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    private static ThreadPoolExecutor newSingleThreadedPoolWithBoundedTaskQueue() {
        return new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
    }
}
