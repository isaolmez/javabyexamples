package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockUsage {

    public static void main(String[] args) {
        final ReadWriteLockUsage readWriteLockUsage = new ReadWriteLockUsage();
        readWriteLockUsage.readWriteLock();
    }

    public void readWriteLock() {
        final ThreadSafeDictionary dictionary = new ThreadSafeDictionary();
        final AtomicInteger index = new AtomicInteger();
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        class Putter implements Runnable {

            @Override
            public void run() {
                dictionary.put(String.valueOf(index.getAndIncrement()), Thread.currentThread().getName());
            }
        }

        class Getter implements Runnable {

            @Override
            public void run() {
                dictionary.get(String.valueOf(index.get() - 1));
            }
        }

        threadPool.execute(new Getter());
        threadPool.execute(new Getter());
        threadPool.execute(new Putter());
        threadPool.execute(new Putter());
        threadPool.execute(new Getter());
        threadPool.execute(new Getter());

        threadPool.shutdown();
    }

    private static class ThreadSafeDictionary {

        private final Map<String, Object> map = new HashMap<>();
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();

        public Object get(String key) {
            System.out.println("Attempting to acquire read lock");
            readLock.lock();
            System.out.println("Acquired read lock");
            try {
                return map.get(key);
            } finally {
                readLock.unlock();
            }
        }

        public Object put(String key, Object value) {
            System.out.println("Attempting to acquire write lock");
            writeLock.lock();
            System.out.println("Acquired write lock");
            try {
                sleep(100);
                return map.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }
    }
}
