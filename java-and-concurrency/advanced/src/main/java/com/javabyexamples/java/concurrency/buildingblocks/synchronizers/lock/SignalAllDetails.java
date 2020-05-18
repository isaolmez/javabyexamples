package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignalAllDetails {

    public static void main(String[] args) throws InterruptedException {
        final SignalAllDetails signalAllDetails = new SignalAllDetails();
        signalAllDetails.allCompletesAfterSignalAll();
        signalAllDetails.oneCompletesAfterSignalAll();
    }

    public void allCompletesAfterSignalAll() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> counter.decrement());
        }

        executorService.submit(() -> counter.incrementBy(20));

        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Count: " + counter.getCount());
    }

    public void oneCompletesAfterSignalAll() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> counter.decrement());
        }

        executorService.submit(() -> counter.incrementBy(1));

        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Count: " + counter.getCount());
    }
}
