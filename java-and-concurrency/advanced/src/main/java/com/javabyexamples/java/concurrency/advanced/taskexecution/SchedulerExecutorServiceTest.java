package com.javabyexamples.java.concurrency.advanced.taskexecution;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SchedulerExecutorServiceTest {

    public static void main(String[] args) {
        new SchedulerExecutorServiceTest().run();
    }

    public void run() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger counter = new AtomicInteger();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService
          .scheduleAtFixedRate(() -> System.out.println(counter.getAndIncrement()), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> scheduledFuture.cancel(true), 10, TimeUnit.SECONDS);
        // TODO close the executor service
    }
}
