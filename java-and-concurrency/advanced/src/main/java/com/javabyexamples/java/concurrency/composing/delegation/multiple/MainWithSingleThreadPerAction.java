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

public class MainWithSingleThreadPerAction {

    private static final int THREAD_COUNT = 2;

    private CountDownLatch done = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        // Non thread-safe
        System.out.println("Non thread-safe");
        Statistics nonThreadSafe = new NonThreadSafeStatistics();
        new MainWithSingleThreadPerAction().run(nonThreadSafe);

        System.out.println("Non thread-safe with different locks");
        Statistics nonThreadSafeDifferentLocks = new DifferentLockNonThreadStatistics();
        new MainWithSingleThreadPerAction().run(nonThreadSafeDifferentLocks);

        // Thread-safe
        System.out.println("Thread-safe with same lock");
        Statistics threadSafeSameLock = new ThreadSafeStatistics();
        new MainWithSingleThreadPerAction().run(threadSafeSameLock);

        System.out.println("Thread-safe with monitor");
        Statistics threadSafeMonitor = new MonitorThreadSafeStatistics();
        new MainWithSingleThreadPerAction().run(threadSafeMonitor);

        System.out.println("Thread-safe with immutable");
        Statistics threadSafeImmutable = new ImmutableThreadSafeStatistics();
        new MainWithSingleThreadPerAction().run(threadSafeImmutable);
    }

    public void run(Statistics statistics) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        executorService.execute(new Updater(statistics));
        executorService.execute(new Checker(statistics));

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
            int i = 0;
            while (i++ < 10000) {
                if (Thread.interrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }

                if (!statistics.invariantHolds()) {
                    System.out.println("Invariant does not hold");
                    break;
                }
            }

            done.countDown();
        }
    }

    public class Updater implements Runnable {

        private final Statistics statistics;

        public Updater(Statistics statistics) {
            this.statistics = statistics;
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10000) {
                if (Thread.interrupted()) {
                    break;
                }

                statistics.update();
            }

            done.countDown();
        }
    }
}
