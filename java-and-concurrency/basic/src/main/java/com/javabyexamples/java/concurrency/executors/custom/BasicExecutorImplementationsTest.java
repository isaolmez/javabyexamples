package com.javabyexamples.java.concurrency.executors.custom;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.getPrintingRunnable;
import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.getRunnable;
import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.runStaticMethods;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.Executor;

public class BasicExecutorImplementationsTest {

    public static void main(String[] args) {
        runStaticMethods(BasicExecutorImplementationsTest.class, 1000);
    }

    public static void runOnMainThread() {
        new DirectExecutor().execute(getRunnable(1));
        new DirectExecutor().execute(getRunnable(2));
    }

    public static void runOnDifferentThread() {
        new ThreadPerTaskExecutor().execute(getRunnable(1));
        new ThreadPerTaskExecutor().execute(getRunnable(2));
    }

    public static void runSequentially() {
        Executor executor = new SerialExecutor(new ThreadPerTaskExecutor());
        executor.execute(getRunnable(1));
        executor.execute(getRunnable(2));
    }

    public static void runWithPause() {
        PausableExecutor executor = new PausableExecutor();
        executor.execute(getPrintingRunnable(1));
        executor.execute(getPrintingRunnable(2));
        System.out.println("Pausing at: " + System.currentTimeMillis());
        executor.pause();
        ConcurrencyUtils.sleep(3000);
        System.out.println("Resuming at: " + System.currentTimeMillis());
        executor.resume();
    }
}
