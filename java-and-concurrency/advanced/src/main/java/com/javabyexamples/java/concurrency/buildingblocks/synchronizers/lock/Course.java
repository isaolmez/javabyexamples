package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Course {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Course course = new Course();

        executorService.submit(() -> course.register("name1"));
        executorService.submit(() -> course.register("name2"));
        executorService.submit(() -> course.register("name3"));

        TimeUnit.SECONDS.sleep(1);

        System.out.println(course.isRegistered("name1"));
        System.out.println(course.isRegistered("name2"));
        System.out.println(course.isRegistered("name3"));

        executorService.shutdown();
    }

    private final Lock lock = new ReentrantLock();
    private final Set<String> registeredNames = new HashSet<>();

    public void register(String name) {
        lock.lock();
        try {
            registeredNames.add(name);
        } finally {
            lock.unlock();
        }
    }

    public boolean isRegistered(String name) {
        lock.lock();
        try {
            return registeredNames.contains(name);
        } finally {
            lock.unlock();
        }
    }
}
