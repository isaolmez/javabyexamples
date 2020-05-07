package com.javabyexamples.java.concurrency.taskexecution;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.ThreadConfined;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeoutTest {

    @Delegated
    private AtomicInteger counter = new AtomicInteger(0);

    @ThreadConfined
    private ThreadLocal<Random> random = new ThreadLocal<>().withInitial(() -> new Random());

    public static void main(String[] args) throws InterruptedException {
        new TimeoutTest().run();
    }

    public void run() throws InterruptedException {
        final int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            tasks.add(() -> {
                Thread.sleep(randomSleepTime());
                return counter.getAndIncrement();
            });
        }

        List<Future<Integer>> futures = executorService.invokeAll(tasks, 1, TimeUnit.SECONDS);
        for (Future<Integer> future : futures) {
            try {
                System.out.printf("Is done: %s. Is cancelled: %s. ", future.isDone(), future.isCancelled());
                System.out.printf("Result: %s", future.get());
            } catch (ExecutionException e) {
                System.out.print("Exception occurred!");
            } catch (CancellationException e) {
                System.out.print("Cancellation occurred!");
            } finally {
                System.out.println();
            }
        }

        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    private int randomSleepTime() {
        return random.get().nextInt(2000);
    }
}
