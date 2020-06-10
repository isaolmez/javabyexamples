package com.javabyexamples.java.concurrency.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsFactoryUsage {

    public static void main(String[] args) throws Exception {
        final ExecutorsFactoryUsage executorsFactoryUsage = new ExecutorsFactoryUsage();
//        executorsFactoryUsage.singleThread();
//        executorsFactoryUsage.configureAfterConstruction();
//        executorsFactoryUsage.preventReconfiguration();
//        executorsFactoryUsage.preventReconfigurationForScheduledExecutorService();
        executorsFactoryUsage.adaptRunnableToCallable();
    }

    public void singleThread() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    public void fixedSize() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }

    public void cached() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    public void singleThreadScheduled() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduled() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    }

    public void configureAfterConstruction() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        if (executorService instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
            threadPool.setMaximumPoolSize(10);
        }
    }

    public void preventReconfiguration() {
        ExecutorService initialThreadPool = Executors.newFixedThreadPool(5);
        final ExecutorService unconfigurableThreadPool = Executors.unconfigurableExecutorService(initialThreadPool);
        if (unconfigurableThreadPool instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) initialThreadPool;
            threadPool.setMaximumPoolSize(10);
        }
    }

    public void preventReconfigurationAgain() {
        ExecutorService initialThreadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>());
        final ExecutorService unconfigurableThreadPool = Executors.unconfigurableExecutorService(initialThreadPool);
        if (unconfigurableThreadPool instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) initialThreadPool;
            threadPool.setMaximumPoolSize(10);
        }
    }

    public void preventReconfigurationForScheduledExecutorService() {
        ScheduledExecutorService initialThreadPool = Executors.newScheduledThreadPool(5);
        final ExecutorService unconfigurableThreadPool = Executors.unconfigurableExecutorService(initialThreadPool);
        if (unconfigurableThreadPool instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) initialThreadPool;
            threadPool.setMaximumPoolSize(10);
        }
    }

    public void adaptRunnableToCallable() throws Exception {
        final Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Doing work...");
            }
        };

        final Callable<Object> callable = Executors.callable(runnableTask);

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<Object> future = executorService.submit(callable);
        final Object result = future.get();
        System.out.println(result);
    }

    public void adaptRunnableToCallableWithReturnValue() throws Exception {
        final Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Doing work...");
            }
        };

        final Callable<Object> callable = Executors.callable(runnableTask, "Done");

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<Object> future = executorService.submit(callable);
        final Object result = future.get();
        System.out.println(result);
    }

    public void defaultThreadFactory() {
        final ThreadFactory threadFactory = Executors.defaultThreadFactory();
    }
}
