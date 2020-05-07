package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentListUsage {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentListUsage listUsage = new ConcurrentListUsage();

        System.out.println("ArrayList");
        listUsage.run(new ArrayList<>());

        System.out.println("Collections.synchronizedList");
        listUsage.run(Collections.synchronizedList(new ArrayList<>()));

        System.out.println("CopyOnWriteArrayList");
        listUsage.run(new CopyOnWriteArrayList<>());
    }

    public void run(List<Object> list) throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch done = new CountDownLatch(10000);

        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                list.add(new Object());

                done.countDown();
            });
        }

        done.await();

        System.out.println("List size: " + list.size());

        executorService.shutdown();
    }
}
