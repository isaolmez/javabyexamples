package com.javabyexamples.java.concurrency.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {

    public static void main(String[] args) throws InterruptedException {
        newCondition();
    }

    public static void newCondition() throws InterruptedException {
        final BlockingQueue blockingQueue = new BlockingQueue(2);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        class Putter implements Runnable {

            @Override
            public void run() {
                try {
                    blockingQueue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Taker implements Runnable {

            @Override
            public void run() {
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        threadPool.execute(new Putter());
        threadPool.execute(new Putter());
        threadPool.execute(new Putter());
        threadPool.execute(new Putter());
        threadPool.execute(new Taker());
        threadPool.execute(new Taker());

        Thread.sleep(1000);
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * Taken from javadoc of Condition
     */
    private static class BlockingQueue {

        private final Object[] items;
        private final Lock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();
        private int putPointer;
        private int takePointer;
        private int count;

        public BlockingQueue(int capacity) {
            items = new Object[capacity];
        }

        public void put(Object value) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length) {
                    System.out.println("Full, waiting");
                    notFull.await();
                    System.out.println("Stop waiting");
                }

                items[putPointer++] = value;
                if (putPointer == items.length) {
                    putPointer = 0;
                }

                count++;
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    System.out.println("Empty, waiting");
                    notEmpty.await();
                    System.out.println("Stop waiting");
                }

                Object result = items[takePointer++];
                if (takePointer == items.length) {
                    takePointer = 0;
                }

                count--;
                notFull.signalAll();
                return result;
            } finally {
                lock.unlock();
            }
        }
    }
}
