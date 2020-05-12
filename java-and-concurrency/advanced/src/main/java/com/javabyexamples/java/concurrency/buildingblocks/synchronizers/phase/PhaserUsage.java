package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.phase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserUsage {

    public static void main(String[] args) throws InterruptedException {
        final PhaserUsage phaserUsage = new PhaserUsage();
        phaserUsage.startAndDonePhases();
    }

    public void startAndDonePhases() throws InterruptedException {
        final int executionCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(executionCount);

        final Phaser phaser = new Phaser(1);

        for (int i = 0; i < executionCount; ++i) {
            phaser.register();
            threadPool.execute(new Worker(phaser));
        }

        TimeUnit.SECONDS.sleep(1);

        phaserProperties(phaser);
        System.out.println("Finalizing phase: " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();

        TimeUnit.SECONDS.sleep(1);

        phaserProperties(phaser);
        System.out.println("Finalizing phase: " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();

        threadPool.shutdown();
    }

    private static void phaserProperties(Phaser phaser) {
        System.out.println("Current phase: " + phaser.getPhase());
        System.out.printf("Registered: %s, Arrived: %s, Unarrived: %s%n", phaser.getRegisteredParties(),
          phaser.getArrivedParties(),
          phaser.getUnarrivedParties());
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
