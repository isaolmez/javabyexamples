package com.javabyexamples.java.concurrency.threadpool;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        FutureCallableTest main = new FutureCallableTest();
        main.runnable(executorService);
        main.exceptionFuture(executorService);
        main.valueFuture(executorService);
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public void exceptionFuture(ExecutorService executorService) {
        Future<Void> exceptionFuture = executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                System.out.println("I am a callable!");
                throw new TestException("Callables can throw checked exception.");
            }
        });

        try {
            exceptionFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void valueFuture(ExecutorService executorService) {
        Future<String> valueFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("I am a callable!");
                return "Hello World";
            }
        });

        try {
            String value = valueFuture.get();
            System.out.printf("Value from future: %s%n", value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void runnable(ExecutorService executorService) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am a runnable!");
                try {
                    throw new TestException("Runnables cannot throw checked exception.");
                } catch (TestException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public static class TestException extends Exception {

        public TestException(String message) {
            super(message);
        }
    }
}
