package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDetails {

    public static void main(String[] args) {
        final CountDownLatchDetails countDownLatchDetails = new CountDownLatchDetails();
        countDownLatchDetails.countDownMultipleTimesOnTheSameThread();
    }

    public void countDownMultipleTimesOnTheSameThread() {
        final CountDownLatch latch = new CountDownLatch(3);

        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
        latch.countDown();
        System.out.println("Latch count: " + latch.getCount());
    }
}
