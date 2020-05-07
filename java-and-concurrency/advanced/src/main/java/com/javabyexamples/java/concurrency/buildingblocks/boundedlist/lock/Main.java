package com.javabyexamples.java.concurrency.buildingblocks.boundedlist.lock;

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
        final BoundedListWithLock<Integer> boundedList = new BoundedListWithLock<>(list, capacity);
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 50; i++) {
            final int current = i;
            executorService.execute(() -> {
                boundedList.add(current);

                System.out.println("Added: " + current);
            });
        }

        executorService.execute(() -> {
            for (int i = 0; i < 50; i++) {
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
            e.printStackTrace();
        }
    }
}
