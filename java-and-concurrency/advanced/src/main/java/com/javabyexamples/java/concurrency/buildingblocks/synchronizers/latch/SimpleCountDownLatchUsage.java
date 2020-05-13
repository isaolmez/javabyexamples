package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleCountDownLatchUsage {

    public static void main(String[] args) throws InterruptedException {
        final SimpleCountDownLatchUsage usage = new SimpleCountDownLatchUsage();
        usage.countDownLatch();
    }

    public void countDownLatch() throws InterruptedException {
        final int threadCount = 5;
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final CountDownLatch startSignal = new CountDownLatch(1);
        for (int i = 0; i < threadCount; i++) {
            threadPool.execute(new Worker(startSignal));
        }

        TimeUnit.SECONDS.sleep(1);

        startSignal.countDown();      // Let all workers proceed

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CountDownLatch startSignal;

        Worker(CountDownLatch startSignal) {
            this.startSignal = startSignal;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                startSignal.await();

                doWork();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted.");
            }
        }

        void doWork() {
            System.out.println("Doing work.");
        }
    }
}
