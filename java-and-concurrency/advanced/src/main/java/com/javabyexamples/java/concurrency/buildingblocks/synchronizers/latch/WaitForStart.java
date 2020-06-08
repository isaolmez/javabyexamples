package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitForStart {

    public static void main(String[] args) throws InterruptedException {
        final WaitForStart waitForStart = new WaitForStart();
        waitForStart.coordinateStart();
    }

    public void coordinateStart() throws InterruptedException {
        final int taskCount = 3;
        final CountDownLatch readySignal = new CountDownLatch(taskCount);
        final CountDownLatch startSignal = new CountDownLatch(1);
        final ExecutorService threadPool = Executors.newFixedThreadPool(taskCount);

        for (int i = 0; i < taskCount; ++i) {
            threadPool.execute(new Worker(readySignal, startSignal));
        }

        readySignal.await();          // Wait for all workers to get ready
        startSignal.countDown();      // Let all workers proceed

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CountDownLatch readySignal;
        private final CountDownLatch startSignal;

        Worker(CountDownLatch readySignal, CountDownLatch startSignal) {
            this.readySignal = readySignal;
            this.startSignal = startSignal;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                readySignal.countDown();

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
