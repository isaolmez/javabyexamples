package com.javabyexamples.java.concurrency.locks;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
 * threads completes.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        ConcurrencyUtils.runStaticMethods(CountDownLatchTest.class, 1000);
    }

    public static void countDownLatch() throws InterruptedException {
        final int executionCount = 5;
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(executionCount);
        final ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < executionCount; ++i) {
            threadPool.execute(new Worker(startSignal, doneSignal));
        }

        startSignal.countDown();      // Let all threads proceed
        doneSignal.await();           // Wait for all to finish
        System.out.println("All done");
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
//        ConcurrencyUtils.shutdownAndAwaitTermination(threadPool);
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
                startSignal.await();
                doWork();
                System.out.println("Done");
                doneSignal.countDown();
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }
        }

        void doWork() {
            System.out.println("Doing work");
        }
    }
}
