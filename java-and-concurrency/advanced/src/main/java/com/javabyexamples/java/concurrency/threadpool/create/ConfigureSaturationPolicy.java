package com.javabyexamples.java.concurrency.threadpool.create;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class ConfigureSaturationPolicy {

    public static void main(String[] args) {
        final ConfigureSaturationPolicy configure = new ConfigureSaturationPolicy();

//        configure.withAbortPolicy();
//        configure.withCallerRunsPolicy();
//        configure.withDiscardOldestPolicy();
        configure.withDiscardPolicy();
    }

    public void withAbortPolicy() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println("Working."));
        }

        threadPool.shutdown();
    }

    public void withCallerRunsPolicy() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));
        threadPool.setRejectedExecutionHandler(new CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println("Thread: " + Thread.currentThread().getName()));
        }

        threadPool.shutdown();
    }

    public void withDiscardOldestPolicy() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));
        threadPool.setRejectedExecutionHandler(new DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            final int current = i;
            threadPool.execute(() -> System.out.println("Task: " + current));
        }

        threadPool.shutdown();
    }

    public void withDiscardPolicy() {
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>(1));
        threadPool.setRejectedExecutionHandler(new DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            final int current = i;
            threadPool.execute(() -> System.out.println("Task: " + current));
        }

        threadPool.shutdown();
    }
}
