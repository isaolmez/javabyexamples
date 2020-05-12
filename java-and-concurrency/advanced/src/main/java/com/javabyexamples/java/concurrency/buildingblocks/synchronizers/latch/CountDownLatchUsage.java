package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
 * threads completes.
 */
public class CountDownLatchUsage {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatchUsage usage = new CountDownLatchUsage();
        usage.countDownLatch();
    }

    public void countDownLatch() throws InterruptedException {
        final int threadCount = 3;
        final CountDownLatch readySignal = new CountDownLatch(threadCount);
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(threadCount);
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; ++i) {
            threadPool.execute(new Worker(readySignal, startSignal, doneSignal));
        }

        readySignal.await();          // Wait for all workers to get ready
        startSignal.countDown();      // Let all workers proceed
        doneSignal.await();           // Wait for all workers to finish
        System.out.println("All done.");

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CountDownLatch readySignal;
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch readySignal, CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.readySignal = readySignal;
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                readySignal.countDown();

                startSignal.await();

                doWork();

                doneSignal.countDown();
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
