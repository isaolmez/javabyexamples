package com.javabyexamples.java.concurrency.advanced.sharing.threadconfinement;

import com.javabyexamples.java.concurrency.advanced.common.ExecutorUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainWithThreadLocal2 {

    private static final int THREAD_COUNT = 100;

    private static ThreadLocal<DateFormat> dateFormatterContainer = ThreadLocal
      .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) throws InterruptedException {
        System.out.println("===> With ThreadLocal");
        runWithThreadLocal(THREAD_COUNT);
        System.out.println();

        System.out.println("===> Without ThreadLocal");
        runWithoutThreadLocal(THREAD_COUNT);
    }

    // Shared global method
    private static DateFormat getDateFormatter() {
        return dateFormatterContainer.get();
    }

    private static void runWithThreadLocal(int threadCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    start.await();
                    DateFormat dateFormatter = getDateFormatter();
                    System.out.println(dateFormatter.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        done.await();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static void runWithoutThreadLocal(int threadCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threadCount);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    start.await();
                    System.out.println(dateFormat.parse("2017-11-02 13:49:11"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        done.await();
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }
}
