package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadOptimizedCourse {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private final Set<String> registeredNames = new HashSet<>();

    public void register(String item) {
        writeLock.lock();
        try {
            registeredNames.add(item);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean isRegistered(String name) {
        readLock.lock();
        try {
            return registeredNames.contains(name);
        } finally {
            readLock.unlock();
        }
    }
}
