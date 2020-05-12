package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDetails {

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreDetails semaphoreDetails = new SemaphoreDetails();
        semaphoreDetails.releaseWhenNotAcquired();
        semaphoreDetails.acquireMultipleTimesOnTheSameThread();
    }

    private final Semaphore semaphore = new Semaphore(10);

    public void releaseWhenNotAcquired() {
        System.out.println("Available permits: " + semaphore.availablePermits());

        semaphore.release();

        System.out.println("Available permits: " + semaphore.availablePermits());
    }

    public void acquireMultipleTimesOnTheSameThread() throws InterruptedException {
        semaphore.acquire();
        System.out.println("Available permits: " + semaphore.availablePermits());

        semaphore.acquire();
        System.out.println("Available permits: " + semaphore.availablePermits());
    }
}
