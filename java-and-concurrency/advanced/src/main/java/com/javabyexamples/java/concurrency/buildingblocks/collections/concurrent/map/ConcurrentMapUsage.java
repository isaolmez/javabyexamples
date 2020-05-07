package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentMapUsage {

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentMapUsage mapUsage = new ConcurrentMapUsage();
        System.out.println("With HashMap");
        for (int i = 0; i < 10; i++) {
            mapUsage.run(new HashMap<>());
        }

        System.out.println("With Collections.synchronizedMap");
        for (int i = 0; i < 10; i++) {
            mapUsage.run(Collections.synchronizedMap(new HashMap<>()));
        }

        System.out.println("With ConcurrentHashMap");
        for (int i = 0; i < 10; i++) {
            mapUsage.run(new ConcurrentHashMap<>());
        }
    }

    public void run(Map<Object, Object> map) throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch done = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int current = i;
            executorService.execute(() -> {
                map.put(current, Boolean.TRUE);

                done.countDown();
            });
        }

        done.await();

        System.out.println("Map size: " + map.size());

        executorService.shutdown();
    }
}
