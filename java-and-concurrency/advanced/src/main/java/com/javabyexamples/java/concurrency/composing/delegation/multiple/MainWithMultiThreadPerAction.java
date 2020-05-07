package com.javabyexamples.java.concurrency.composing.delegation.multiple;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.nonthreadsafe.DifferentLockNonThreadStatistics;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.nonthreadsafe.NonThreadSafeStatistics;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.threadsafe.ImmutableThreadSafeStatistics;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.threadsafe.MonitorThreadSafeStatistics;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.threadsafe.ThreadSafeStatistics;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWithMultiThreadPerAction {

    private static final int THREAD_COUNT = 1000;

    private CountDownLatch done = new CountDownLatch(THREAD_COUNT);
    private CountDownLatch start = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        // Non thread-safe
        System.out.println("Non thread-safe");
        Statistics nonThreadSafe = new NonThreadSafeStatistics();
        new MainWithMultiThreadPerAction().run(nonThreadSafe);

        System.out.println("Non thread-safe with different locks");
        Statistics nonThreadSafeDifferentLocks = new DifferentLockNonThreadStatistics();
        new MainWithMultiThreadPerAction().run(nonThreadSafeDifferentLocks);

        // Thread-safe
        System.out.println("Thread-safe with same lock");
        Statistics threadSafeSameLock = new ThreadSafeStatistics();
        new MainWithMultiThreadPerAction().run(threadSafeSameLock);

        System.out.println("Thread-safe with monitor");
        Statistics threadSafeMonitor = new MonitorThreadSafeStatistics();
        new MainWithMultiThreadPerAction().run(threadSafeMonitor);

        System.out.println("Thread-safe with immutable");
        Statistics threadSafeImmutable = new ImmutableThreadSafeStatistics();
        new MainWithMultiThreadPerAction().run(threadSafeImmutable);
    }

    public void run(Statistics statistics) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT / 2; i++) {
            executorService.execute(new Updater(statistics));
            executorService.execute(new Checker(statistics));
        }

        start.countDown();
        done.await();
        statistics.print();
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public class Checker implements Runnable {

        private final Statistics statistics;

        public Checker(Statistics statistics) {
            this.statistics = statistics;
        }

        @Override
        public void run() {
            try {
                start.await();
                if (!statistics.invariantHolds()) {
                    System.out.println("Invariant does not hold");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                done.countDown();
            }
        }
    }

    public class Updater implements Runnable {

        private final Statistics statistics;

        public Updater(Statistics statistics) {
            this.statistics = statistics;
        }

        @Override
        public void run() {
            try {
                start.await();
                statistics.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                done.countDown();
            }
        }
    }
}
