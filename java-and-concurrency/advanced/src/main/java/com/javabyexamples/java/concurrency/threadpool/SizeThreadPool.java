package com.javabyexamples.java.concurrency.threadpool;

import com.javabyexamples.java.concurrency.common.StopWatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SizeThreadPool {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Processors: " + Runtime.getRuntime().availableProcessors());

        final SizeThreadPool sizeThreadPool = new SizeThreadPool();
        final List<Integer> numbers = sizeThreadPool.getRandomList(10000);
        sizeThreadPool.run(1, 1000, numbers);
        sizeThreadPool.run(2, 1000, numbers);
        sizeThreadPool.run(4, 1000, numbers);
        sizeThreadPool.run(8, 1000, numbers);
        sizeThreadPool.run(16, 1000, numbers);
        sizeThreadPool.run(32, 1000, numbers);
        sizeThreadPool.run(64, 1000, numbers);
        sizeThreadPool.run(128, 1000, numbers);
        sizeThreadPool.run(256, 1000, numbers);
        sizeThreadPool.run(512, 1000, numbers);
    }

    public void run(int threadCount, int taskCount, List<Integer> numbers) throws InterruptedException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch done = new CountDownLatch(taskCount);

        for (int i = 0; i < taskCount; i++) {
            executorService.execute(() -> {
                Collections.sort(new ArrayList<>(numbers));

                done.countDown();
            });
        }

        done.await();
        System.out.printf("Thread count: %s, Elapsed Time: %s%n", threadCount, stopWatch.elapsedTime());

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    public List<Integer> getRandomList(int size) {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(random.nextInt(size));
        }

        return result;
    }
}
