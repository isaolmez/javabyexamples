package com.javabyexamples.java.concurrency.threadpool.submittask;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UseCompletionService {

    public static void main(String[] args) throws InterruptedException {
        new UseCompletionService().run();
    }

    public void run() throws InterruptedException {
        final int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < threadCount; i++) {
            completionService.submit(() -> {
                final int sleepTime = randomSleepTime();
                Thread.sleep(sleepTime);
                return sleepTime;
            });
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                Future<Integer> result = completionService.take();

                System.out.println("Result: " + result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    private int randomSleepTime() {
        return new Random().nextInt(10000);
    }
}
