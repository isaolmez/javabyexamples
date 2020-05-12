package com.javabyexamples.java.concurrency.threadpool.create;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class ConfigureThreadPoolExecutor {

    private static final int CORE_SIZE = 5;
    private static final int MAX_SIZE = 100;
    private static final int QUEUE_MAX_SIZE = 10;

    public static void main(String[] args) {
        final ConfigureThreadPoolExecutor configure = new ConfigureThreadPoolExecutor();
//        configure.configureThreadPool();
//        configure.configurePreConfiguredThreadPool();

//        configure.executeOne();
//        configure.executeEqualToCoreSize();
//        configure.executeGreaterThanCoreSize();
//        configure.executeGreaterThanQueueSize();

        configure.preStartCoreThreads();

//        configure.rejectTask();
    }

    public void configureThreadPool() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new AbortPolicy());
        threadPool.setMaximumPoolSize(12);
        threadPool.setCorePoolSize(11);
        threadPool.setKeepAliveTime(1, TimeUnit.SECONDS);
        threadPool.setRejectedExecutionHandler(new CallerRunsPolicy());
        threadPool.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
    }

    public void configurePreConfiguredThreadPool() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        if (executorService instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
            threadPool.setMaximumPoolSize(12);
            threadPool.setCorePoolSize(11);
            threadPool.setKeepAliveTime(1, TimeUnit.SECONDS);
            threadPool.setRejectedExecutionHandler(new CallerRunsPolicy());
            threadPool.setThreadFactory(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            });
        }
    }

    public void unconfigurableThreadPool() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>());
        final ExecutorService unconfigurableThreadPool = Executors.unconfigurableExecutorService(threadPool);
    }

    public void executeOne() {
        ThreadPoolExecutor threadPool = newThreadPool();

        threadPool.execute(new PrintTask());

        ExecutorUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeEqualToCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < CORE_SIZE + 1; i++) {
            threadPool.execute(new PrintTask());
        }

        ExecutorUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeGreaterThanCoreSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < CORE_SIZE * 2; i++) {
            threadPool.execute(new PrintTask());
        }

        ExecutorUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void executeGreaterThanQueueSize() {
        ThreadPoolExecutor threadPool = newThreadPool();

        for (int i = 0; i < QUEUE_MAX_SIZE * 3; i++) {
            threadPool.execute(new PrintTask());
        }

        ExecutorUtils.shutdownAndAwaitTermination(threadPool);
    }

    public void preStartCoreThreads() {
        ThreadPoolExecutor threadPool = newThreadPool();
        printThreadPoolProperties(threadPool);

        threadPool.prestartAllCoreThreads();
        printThreadPoolProperties(threadPool);

        ExecutorUtils.shutdownAndAwaitTermination(threadPool);
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

    private ThreadPoolExecutor newThreadPool() {
        return new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, 60, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(QUEUE_MAX_SIZE));
    }

    private void printThreadPoolProperties(ThreadPoolExecutor threadPool) {
        System.out.println("Current pool size: " + threadPool.getPoolSize());
        System.out.println("Core pool size: " + threadPool.getCorePoolSize());
        System.out.println("Maximum pool size: " + threadPool.getMaximumPoolSize());
        System.out.println("Keep alive time: " + threadPool.getKeepAliveTime(TimeUnit.SECONDS));
        System.out.println("Queue size: " + threadPool.getQueue().remainingCapacity());
        System.out.println("Saturation Policy: " + threadPool.getRejectedExecutionHandler().getClass());
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
