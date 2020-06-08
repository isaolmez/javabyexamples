package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitForStart {

    public static void main(String[] args) throws Exception {
        final WaitForStart waitForStart = new WaitForStart();
        waitForStart.coordinateStart();
        waitForStart.coordinateStartUsingMain();
    }

    public void coordinateStart() {
        final int taskCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(taskCount);

        final CyclicBarrier barrier = new CyclicBarrier(taskCount,
          () -> System.out.println("All ready to continue!"));

        for (int i = 0; i < taskCount; ++i) {
            threadPool.execute(new Worker(barrier));
        }

        threadPool.shutdown();
    }

    public void coordinateStartUsingMain() throws Exception {
        final int taskCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(taskCount);

        final CyclicBarrier barrier = new CyclicBarrier(taskCount + 1,
          () -> System.out.println("All ready to continue!"));

        for (int i = 0; i < taskCount; ++i) {
            threadPool.execute(new Worker(barrier));
        }

        barrier.await();

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
