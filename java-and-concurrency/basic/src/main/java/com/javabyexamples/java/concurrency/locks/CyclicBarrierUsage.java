package com.javabyexamples.java.concurrency.locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierUsage {

    public static void main(String[] args) {
        final CyclicBarrierUsage cyclicBarrierUsage = new CyclicBarrierUsage();
        cyclicBarrierUsage.startAndDoneBarriers();
    }

    public void startAndDoneBarriers() {
        final int executionCount = 3;
        final CyclicBarrier startBarrier = new CyclicBarrier(executionCount,
          () -> System.out.println("All ready to continue!"));
        final ExecutorService threadPool = Executors.newFixedThreadPool(executionCount);

        for (int i = 0; i < executionCount; ++i) {
            threadPool.execute(new Worker(startBarrier));
        }

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CyclicBarrier startBarrier;

        Worker(CyclicBarrier startBarrier) {
            this.startBarrier = startBarrier;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                startBarrier.await();

                doWork();

                startBarrier.await();
                System.out.println("Done.");
            } catch (InterruptedException | BrokenBarrierException ex) {
                System.out.println("Error");
            }
        }

        public void doWork() {
            System.out.println("Doing work.");
        }
    }
}
