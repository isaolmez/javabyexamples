package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockDetails {

    public static void main(String[] args) {
        final LockDetails lockDetails = new LockDetails();
        lockDetails.unlockWhenNotLocked();
        lockDetails.lockMultipleTimesOnTheSameThread();
    }

    private final ReentrantLock lock = new ReentrantLock();

    public void unlockWhenNotLocked() {
        System.out.println("Currently held: " + lock.isHeldByCurrentThread());
        lock.unlock();
    }

    public void lockMultipleTimesOnTheSameThread() {
        lock.lock();
        System.out.println("Acquired lock.");
        lock.lock();
        System.out.println("Acquired lock again, since it's reentrant.");
    }
}
