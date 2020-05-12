package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

public class LatchDetails {

    public static void main(String[] args) {
        final LatchDetails latchDetails = new LatchDetails();
        latchDetails.countDownMultipleTimesOnTheSameThread();
    }

    public void countDownMultipleTimesOnTheSameThread() {
        final CountDownLatch latch = new CountDownLatch(10);

        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
    }
}
