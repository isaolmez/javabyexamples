package com.javabyexamples.java.concurrency.taskexecution;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.ThreadConfined;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletionServiceTest {

    @Delegated
    private final AtomicInteger counter = new AtomicInteger(0);

    @ThreadConfined
    private final ThreadLocal<Random> random = new ThreadLocal<>().withInitial(Random::new);

    public static void main(String[] args) throws InterruptedException {
        new CompletionServiceTest().run();
    }

    public void run() throws InterruptedException {
        final int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        CountDownLatch done = new CountDownLatch(10);
        printTime();

        for (int i = 0; i < threadCount; i++) {
            completionService.submit(() -> {
                Thread.sleep(randomSleepTime());
                try {
                    return counter.getAndIncrement();
                } finally {
                    done.countDown();
                }
            });
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                Future<Integer> result = completionService.take();
                System.out.printf("Result: %s%n", result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        printTime();
        done.await();
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    private int randomSleepTime() {
        return random.get().nextInt(10000);
    }

    private void printTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("Time: %s%n", dateFormat.format(new Date()));
    }
}
