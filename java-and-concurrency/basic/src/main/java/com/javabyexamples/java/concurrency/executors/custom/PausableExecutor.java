package com.javabyexamples.java.concurrency.executors.custom;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PausableExecutor extends ThreadPoolExecutor {

    private boolean isPaused = false;
    private Lock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        PausableExecutor executor = new PausableExecutor();
        System.out.println("Pausing at: " + System.currentTimeMillis());
        executor.pause();
        executor.execute(() -> System.out.println("Task1"));
        executor.execute(() -> System.out.println("Task2"));
        Thread.sleep(1000);

        System.out.println("Resuming at: " + System.currentTimeMillis());
        executor.resume();
    }

    public PausableExecutor() {
        super(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            t.interrupt();
        } finally {
            pauseLock.unlock();
        }
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signal();
        } finally {
            pauseLock.unlock();
        }
    }
}
