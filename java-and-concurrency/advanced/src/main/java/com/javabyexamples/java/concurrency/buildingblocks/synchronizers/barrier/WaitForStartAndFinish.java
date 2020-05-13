package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitForStartAndFinish {

    public static void main(String[] args) throws InterruptedException {
        final WaitForStartAndFinish waitForStartAndFinish = new WaitForStartAndFinish();
        waitForStartAndFinish.startAndDoneBarriers();
    }

    public void startAndDoneBarriers() throws InterruptedException {
        final int executionCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(executionCount);

        final CyclicBarrier barrier = new CyclicBarrier(executionCount,
          () -> System.out.println("All ready to continue!"));

        for (int i = 0; i < executionCount; ++i) {
            threadPool.execute(new Worker(barrier));
        }

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CyclicBarrier barrier;

        Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                barrier.await();

                doWork();

                barrier.await();
                System.out.println("Done.");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted.");
            } catch (BrokenBarrierException ex) {
                System.out.println("Broken barrier.");
            }
        }

        public void doWork() {
            System.out.println("Doing work.");
        }
    }
}
