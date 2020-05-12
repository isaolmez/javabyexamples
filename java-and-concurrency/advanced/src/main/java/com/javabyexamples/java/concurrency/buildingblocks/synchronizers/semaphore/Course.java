package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.semaphore;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Course {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Course course = new Course(10);

        for (int i = 0; i < 10; i++) {
            final int current = i;

            executorService.submit(() -> course.register("name" + current));

            executorService.submit(() -> course.unregister("name" + current));
        }

        TimeUnit.SECONDS.sleep(1);

        executorService.shutdown();
    }

    private final Semaphore seats;
    private final Set<String> registeredNames = ConcurrentHashMap.newKeySet();

    public Course(int seatCount) {
        seats = new Semaphore(seatCount);
    }

    public boolean register(String name) throws InterruptedException {
        seats.acquire();

        return registeredNames.add(name);
    }

    public boolean unregister(String name) {
        try {
            return registeredNames.contains(name);
        } finally {
            seats.release();
        }
    }
}
