package com.javabyexamples.java.concurrency.advanced.buildingblocks.synchronizers.barrier;

import com.javabyexamples.java.concurrency.advanced.common.ExecutorUtils;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }

    public void run() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CyclicBarrier barrier = new CyclicBarrier(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    barrier.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1000);
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }
}
