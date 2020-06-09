package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedAccess {

    public void doInsert(Set<Object> set) throws InterruptedException {
        final int taskCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        final CyclicBarrier startBarrier = new CyclicBarrier(taskCount);

        for (int i = 0; i < taskCount; i++) {
            executorService.execute(() -> {
                try {
                    startBarrier.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                } catch (BrokenBarrierException e) {
                    System.out.println(e.getMessage());
                }

                set.add("hi");
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Set size: " + set.size());
    }

    public void doIterate(Set<Object> set) {
        final int taskCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        final CyclicBarrier startBarrier = new CyclicBarrier(taskCount);

        for (int i = 0; i < taskCount; i++) {
            executorService.execute(() -> {
                try {
                    startBarrier.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                } catch (BrokenBarrierException e) {
                    System.out.println(e.getMessage());
                }

                set.add("hi");
                for (Object element : set) {
                    // Do something.
                }
            });
        }

        executorService.shutdown();
    }
}
