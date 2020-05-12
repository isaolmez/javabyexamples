package com.javabyexamples.java.concurrency.executors;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConfigureThreadPoolExecutor {

    private static final int SINGLE = 1;
    private static final int CORE_SIZE = 5;
    private static final int MAX_SIZE = 100;
    private static final int QUEUE_MAX_SIZE = 10;

    public static void main(String[] args) {
        final ConfigureThreadPoolExecutor usage = new ConfigureThreadPoolExecutor();
        usage.executeOne();
//        usage.executeEqualToCoreSize();
//        usage.executeGreaterThanCoreSize();
//        usage.executeGreaterThanQueueSize();
//        usage.preStartCoreThreads();
//        usage.rejectTask();
    }

    public void executeOne() {
        ThreadPoolExecutor threadPool = newThreadPool();

        threadPool.execute(new PrintTask());

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < CORE_SIZE + 1; i++) {
            threadPool.execute(new PrintTask());
        }

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < CORE_SIZE * 2; i++) {
            threadPool.execute(new PrintTask());
        }

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeGreaterThanQueueSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < QUEUE_MAX_SIZE * 3; i++) {
            threadPool.execute(new PrintTask());
        }

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void preStartCoreThreads() {
        ThreadPoolExecutor threadPool = newThreadPool();

        threadPool.prestartAllCoreThreads();

        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void rejectTask() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(new PrintTask());
            }
        } finally {
            threadPool.shutdown();
        }
    }

    private static ThreadPoolExecutor newThreadPool() {
        return new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, 60, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(QUEUE_MAX_SIZE));
    }

    private static ThreadPoolExecutor newSingleThreadedPoolWithBoundedTaskQueue() {
        return new ThreadPoolExecutor(SINGLE, SINGLE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
    }

    public static class PrintTask implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }

            System.out.println("Running in thread: " + Thread.currentThread().getName());
        }
    }
}
