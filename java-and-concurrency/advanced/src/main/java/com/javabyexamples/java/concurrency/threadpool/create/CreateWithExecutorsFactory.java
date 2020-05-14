package com.javabyexamples.java.concurrency.threadpool.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CreateWithExecutorsFactory {

    public static void main(String[] args) {
        final CreateWithExecutorsFactory createWithExecutorsFactory = new CreateWithExecutorsFactory();
//        System.out.println("## singleThreaded()");
//        createWithExecutorsFactory.singleThreadedPool();

//        System.out.println("## fixedSizePool()");
//        createWithExecutorsFactory.fixedSizePool();
//        createWithExecutorsFactory.fixedSizePoolThreadCreation();

//        System.out.println("## cachedPool()");
//        createWithExecutorsFactory.cachedPool();
//        createWithExecutorsFactory.cachedPoolThreadCreation();
//
//        System.out.println("## scheduled()");
        createWithExecutorsFactory.scheduledPool();
    }

    public void singleThreadedPool() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        printThreadPoolProperties(executorService);

        executorService.shutdown();
    }

    public void fixedSizePool() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        printThreadPoolProperties(executorService);

        executorService.shutdown();
    }

    public void fixedSizePoolThreadCreation() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        printPoolSize(executorService);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return "done";
            });

            printPoolSize(executorService);
        }

        executorService.shutdown();
    }

    public void cachedPool() {
        final ExecutorService executorService = Executors.newCachedThreadPool();

        printThreadPoolProperties(executorService);

        executorService.shutdown();
    }

    public void cachedPoolThreadCreation() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        printPoolSize(executorService);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return "done";
            });

            printPoolSize(executorService);
        }

        executorService.shutdown();
    }

    public void scheduledPool() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        printThreadPoolProperties(scheduledExecutorService);

        scheduledExecutorService.shutdown();
    }

    private void printPoolSize(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
            System.out.println("Current pool size: " + threadPool.getPoolSize());
            System.out.println("Core pool size: " + threadPool.getCorePoolSize());
            System.out.println("Maximum pool size: " + threadPool.getMaximumPoolSize());
        }
    }

    private void printThreadPoolProperties(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
            System.out.println("Current pool size: " + threadPool.getPoolSize());
            System.out.println("Core pool size: " + threadPool.getCorePoolSize());
            System.out.println("Maximum pool size: " + threadPool.getMaximumPoolSize());
            System.out.println("Keep alive time: " + threadPool.getKeepAliveTime(TimeUnit.SECONDS));
            System.out.println("Queue size: " + threadPool.getQueue().remainingCapacity());
            System.out.println("Saturation Policy: " + threadPool.getRejectedExecutionHandler().getClass());
        }
    }
}
