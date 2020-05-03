package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        readWriteLock();
    }

    public static void readWriteLock() throws InterruptedException {
        final RWDictionary dictionary = new RWDictionary();
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
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * Taken from javadoc of ReentrantReadWriteLock
     */
    private static class RWDictionary {

        private final Map<String, Object> map = new TreeMap<>();
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

        public String[] allKeys() {
            readLock.lock();
            try {
                return map.keySet().toArray(new String[0]);
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

        public void clear() {
            writeLock.lock();
            try {
                map.clear();
            } finally {
                writeLock.unlock();
            }
        }
    }
}
