package com.javabyexamples.java.concurrency.buildingblocks.boundedlist.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        new Main().run(new ArrayList<>(), 10);
    }

    public void run(List<Integer> list, int capacity) {
        final int threadCount = 100;
        final BoundedListWithBlockingQueue<Integer> boundedList = new BoundedListWithBlockingQueue<>(list, capacity);

        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int current = i;
            executorService.execute(() -> {
                try {
                    boundedList.add(current);

                    System.out.println("Added: " + current);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted.");
                }
            });
        }

        executorService.execute(() -> {
            for (int i = 0; i < threadCount; i++) {
                boundedList.remove(i);

                System.out.println("Attempted to remove: " + i);

                sleep(1);
            }
        });

        executorService.shutdown();
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted.");
        }
    }
}
