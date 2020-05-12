package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSetUsage {

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentSetUsage setUsage = new ConcurrentSetUsage();
        System.out.println("With HashSet");
        for (int i = 0; i < 10; i++) {
            setUsage.run(new HashSet<>());
        }

        System.out.println("With Collections.synchronizedSet");
        for (int i = 0; i < 10; i++) {
            setUsage.run(Collections.synchronizedSet(new HashSet<>()));
        }

        System.out.println("With ConcurrentHashMap-backed Set");
        for (int i = 0; i < 10; i++) {
            setUsage.run(ConcurrentHashMap.newKeySet());
        }

        System.out.println("With CopyOnWriteArraySet");
        for (int i = 0; i < 10; i++) {
            setUsage.run(new CopyOnWriteArraySet<>());
        }
    }

    public void run(Set<Object> set) throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch done = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int current = i;
            executorService.execute(() -> {
                set.add(current);

                done.countDown();
            });
        }

        done.await();

        System.out.println("Set size: " + set.size());

        executorService.shutdown();
    }
}
