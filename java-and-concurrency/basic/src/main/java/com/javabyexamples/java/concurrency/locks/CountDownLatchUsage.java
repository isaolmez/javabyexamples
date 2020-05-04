package com.javabyexamples.java.concurrency.locks;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

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
        final int executionCount = 3;
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(executionCount);
        final ExecutorService threadPool = Executors.newFixedThreadPool(executionCount);

        for (int i = 0; i < executionCount; ++i) {
            threadPool.execute(new Worker(startSignal, doneSignal));
        }

        sleep(100);
        startSignal.countDown();      // Let all workers proceed
        doneSignal.await();           // Wait for all workers to finish
        System.out.println("All done.");

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                startSignal.await();

                doWork();

                doneSignal.countDown();
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }
        }

        void doWork() {
            System.out.println("Doing work.");
        }
    }
}
