package com.javabyexamples.java.concurrency.threadpool.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CreateWithExecutorsFactory {

    public static void main(String[] args) {
        final CreateWithExecutorsFactory createWithExecutorsFactory = new CreateWithExecutorsFactory();
        System.out.println("## singleThreaded()");
        createWithExecutorsFactory.singleThreaded();

        System.out.println("## fixedSize()");
        createWithExecutorsFactory.fixedSize();

        System.out.println("## cached()");
        createWithExecutorsFactory.cached();

        System.out.println("## scheduled()");
        createWithExecutorsFactory.scheduled();
    }

    public void singleThreaded() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        printThreadPoolProperties(executorService);
    }

    public void fixedSize() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        printThreadPoolProperties(executorService);
    }

    public void cached() {
        final ExecutorService executorService = Executors.newCachedThreadPool();

        printThreadPoolProperties(executorService);
    }

    public void scheduled() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        printThreadPoolProperties(scheduledExecutorService);
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
