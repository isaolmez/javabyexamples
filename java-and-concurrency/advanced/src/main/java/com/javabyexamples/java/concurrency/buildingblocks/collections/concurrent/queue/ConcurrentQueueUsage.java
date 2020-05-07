package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentQueueUsage {

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentQueueUsage queueUsage = new ConcurrentQueueUsage();
        System.out.println("With LinkedList");
        for (int i = 0; i < 10; i++) {
            queueUsage.run(new LinkedList<>());
        }

        System.out.println("With ConcurrentLinkedQueue");
        for (int i = 0; i < 10; i++) {
            queueUsage.run(new ConcurrentLinkedQueue<>());
        }

        System.out.println("With LinkedBlockingQueue");
        for (int i = 0; i < 10; i++) {
            queueUsage.run(new LinkedBlockingQueue<>());
        }
    }

    public void run(Queue<Object> queue) throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch done = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int current = i;
            executorService.execute(() -> {
                queue.offer(current);

                done.countDown();
            });
        }

        done.await();

        System.out.println("Queue Size: " + queue.size());

        executorService.shutdown();
    }
}
