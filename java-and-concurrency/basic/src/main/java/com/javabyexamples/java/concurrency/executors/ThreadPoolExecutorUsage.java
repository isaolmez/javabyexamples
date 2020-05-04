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

public class ThreadPoolExecutorUsage {

    private static final int SINGLE = 1;
    private static final int CORE_SIZE = 3;
    private static final int MAX_SIZE = 6;

    public static void main(String[] args) {
//        ConcurrencyUtils.runStaticMethods(ThreadPoolExecutorTest.class, 3000);
        final ThreadPoolExecutorUsage usage = new ThreadPoolExecutorUsage();
        usage.executeOne();
        usage.executeEqualToCoreSize();
        usage.executeGreaterThanCoreSize();
        usage.preStartCoreThreads();
        usage.rejectTask();
    }

    public void executeOne() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        threadPool.execute(ConcurrencyUtils.getRunnable(1));
        printPoolSize(threadPool);
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        IntStream.range(1, CORE_SIZE + 1).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order)));
        printPoolSize(threadPool);
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        IntStream.range(1, MAX_SIZE * 2).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order)));
        printPoolSize(threadPool);
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void preStartCoreThreads() {
        ThreadPoolExecutor threadPool = newThreadPool();
        threadPool.prestartAllCoreThreads();
        printPoolSize(threadPool);
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void rejectTask() {
        ThreadPoolExecutor threadPool = newSingleThreadedPoolWithBoundedTaskQueue();
        printPoolSize(threadPool);
        IntStream.range(1, 5).forEach(order -> threadPool.execute(ConcurrencyUtils.getRunnable(order, 1000)));
        printPoolSize(threadPool);
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void submitOne() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        Future<String> result = threadPool.submit(ConcurrencyUtils.getCallable(1));
        printPoolSize(threadPool);

        try {
            System.out.printf("Result: %s%n", result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void submitEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        List<Future<String>> results = Stream.of(1, 2, 3)
          .map(order -> threadPool.submit(ConcurrencyUtils.getCallable(order)))
          .collect(Collectors.toList());
        printPoolSize(threadPool);

        results.forEach(future -> {
            try {
                System.out.printf("Result: %s%n", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void submitGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printPoolSize(threadPool);
        List<Future<String>> results = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
          .map(order -> threadPool.submit(ConcurrencyUtils.getCallable(order)))
          .collect(Collectors.toList());
        printPoolSize(threadPool);

        results.forEach(future -> {
            try {
                System.out.printf("Result: %s%n", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    private static void printPoolSize(ThreadPoolExecutor threadPool) {
        System.out.printf("Thread size: %s%n", threadPool.getPoolSize());
    }

    private static ThreadPoolExecutor newThreadPool() {
        return new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    private static ThreadPoolExecutor newSingleThreadedPoolWithBoundedTaskQueue() {
        return new ThreadPoolExecutor(SINGLE, SINGLE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
    }
}
