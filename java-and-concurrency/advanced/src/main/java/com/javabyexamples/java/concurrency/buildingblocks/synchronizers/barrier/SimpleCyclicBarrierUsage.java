package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleCyclicBarrierUsage {

    public static void main(String[] args) throws InterruptedException {
        final SimpleCyclicBarrierUsage cyclicBarrierUsage = new SimpleCyclicBarrierUsage();
        cyclicBarrierUsage.startBarrier();
    }

    public void startBarrier() throws InterruptedException {
        final int threadCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final CyclicBarrier start = new CyclicBarrier(threadCount,
          () -> System.out.println("All ready to continue!"));

        for (int i = 0; i < threadCount; ++i) {
            threadPool.execute(new Worker(start));
        }

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final CyclicBarrier start;

        Worker(CyclicBarrier start) {
            this.start = start;
        }

        public void run() {
            try {
                System.out.println("Ready to start.");
                start.await();

                doWork();
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
