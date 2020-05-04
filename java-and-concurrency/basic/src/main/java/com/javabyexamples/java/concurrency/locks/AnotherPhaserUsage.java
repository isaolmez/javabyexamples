package com.javabyexamples.java.concurrency.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class AnotherPhaserUsage {

    public static void main(String[] args) {
        final AnotherPhaserUsage phaserUsage = new AnotherPhaserUsage();
        phaserUsage.startAndDonePhases();
    }

    public void startAndDonePhases() {
        final int executionCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(executionCount);

        final Phaser phaser = new Phaser(executionCount);

        for (int i = 0; i < executionCount; ++i) {
            threadPool.execute(new Worker(phaser));
        }

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

            phaser.arriveAndAwaitAdvance();
            System.out.println("Done.");
        }

        public void doWork() {
            System.out.println("Doing work.");
        }
    }
}
