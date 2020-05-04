package com.javabyexamples.java.concurrency.future;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.printThreadName;
import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import com.javabyexamples.java.concurrency.utils.ConcurrencyUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureUsage {

    private static final String RESULT = "Test result";

    public static void main(String[] args) {
//        ConcurrencyUtils.runStaticMethods(FutureUsage.class, 1000);
        final FutureUsage futureUsage = new FutureUsage();
    }

    public void executeCallableInNewThread() throws Exception {
        String result = Executors.newSingleThreadExecutor().submit(getCallable()).get();

        System.out.printf("Result: %s%n", result);
    }

    public void executeRunnableInNewThread() {
        Executors.newSingleThreadExecutor().execute(getRunnable());
    }

    public void executeRunnableInNewDedicatedThread() {
        new Thread(getRunnable()).start();
    }

    public void executeFutureTaskInNewThread() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = getFutureTask();
        futureTask.run();
        Executors.newSingleThreadExecutor().execute(futureTask);
        System.out.printf("Is Done: %s%n", futureTask.isDone());
        String result = futureTask.get();
        System.out.printf("Is Done: %s%n", futureTask.isDone());

        System.out.printf("Result: %s%n", result);
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                printThreadName();
                sleep();
                System.out.printf("Result: %s%n", RESULT);
            }
        };
    }

    private Callable<String> getCallable() {
        return new Callable<String>() {
            public String call() throws Exception {
                printThreadName();
                sleep();
                return RESULT;
            }
        };
    }

    private FutureTask<String> getFutureTask() {
        return new FutureTask<>(getCallable());
    }
}
