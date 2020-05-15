package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDetails {

    public static void main(String[] args) throws InterruptedException {
        final LockDetails lockDetails = new LockDetails();
//        lockDetails.lockUsage();
//        lockDetails.lockReentrancy(5);
//        lockDetails.unlockWhenNotLocked();

        lockDetails.lockInterruptiblyUsage();
        lockDetails.tryLockUsage();
    }

    private final ReentrantLock lock = new ReentrantLock();

    public void lockUsage() {
        lock.lock();
        try {
            doWork();
        } finally {
            lock.unlock();
        }
    }

    public void lockReentrancy(int times) {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " acquired: " + lock.getHoldCount());
        try {
            if (times != 0) {
                lockReentrancy(times - 1);
            }
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released: " + lock.getHoldCount());
        }
    }

    public void unlockWhenNotLocked() {
        System.out.println("Currently held: " + lock.isHeldByCurrentThread());
        lock.unlock();
    }


    public void lockInterruptiblyUsage() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            doWork();
        } finally {
            lock.unlock();
        }
    }

    public void tryLockUsage() {
        if (lock.tryLock()) {
            try {
                doWork();
            } finally {
                lock.unlock();
            }
        }
    }

    public void tryLockWithTimeoutUsage() throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                doWork();
            } finally {
                lock.unlock();
            }
        }
    }

    private void doWork() {
        System.out.println("Doing work.");
    }
}
