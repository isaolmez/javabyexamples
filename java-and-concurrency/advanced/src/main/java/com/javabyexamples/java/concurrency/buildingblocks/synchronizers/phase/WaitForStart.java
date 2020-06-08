package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.phase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class WaitForStart {

    public static void main(String[] args) {
        final WaitForStart waitForStart = new WaitForStart();
        waitForStart.coordinateStart();
        waitForStart.coordinateStartUsingMain();
    }

    public void coordinateStart() {
        final int taskCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(taskCount);

        final Phaser phaser = new Phaser(taskCount);

        for (int i = 0; i < taskCount; ++i) {
            threadPool.execute(new Worker(phaser));
        }

        threadPool.shutdown();
    }

    public void coordinateStartUsingMain() {
        final int taskCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(taskCount);

        final Phaser phaser = new Phaser(taskCount + 1);

        for (int i = 0; i < taskCount; ++i) {
            threadPool.execute(new Worker(phaser));
        }

        phaser.arriveAndAwaitAdvance();

        threadPool.shutdown();
    }

    static class Worker implements Runnable {

        private final Phaser phaser;

        Worker(Phaser phaser) {
            this.phaser = phaser;
        }

        public void run() {
            System.out.println("Ready to start.");
            phaser.arriveAndAwaitAdvance();

            doWork();
        }

        public void doWork() {
            System.out.println("Doing work.");
        }
    }
}
