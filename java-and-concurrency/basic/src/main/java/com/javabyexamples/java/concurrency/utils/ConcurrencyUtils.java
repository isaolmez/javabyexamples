package com.javabyexamples.java.concurrency.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrencyUtils {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep() {
        sleep(100);
    }

    public static void printThreadName() {
        System.out.printf("Thread: %s%n", Thread.currentThread().getName());
    }

    public static void shutdownAndAwaitTermination(ExecutorService threadPool) {
        threadPool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            threadPool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public static <T> void runStaticMethods(Class<T> target) {
        Method[] declaredMethods = target.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers())
              && Modifier.isPublic(method.getModifiers())
              && !method.getName().equals("main")) {
                try {
                    System.out.printf("=> Method: %s%n", method.getName());
                    method.invoke(null);
                    System.out.printf("=============%n");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static <T> void runStaticMethods(Class<T> target, long waitBetweenCalls) {
        Method[] declaredMethods = target.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers())
              && Modifier.isPublic(method.getModifiers())
              && !method.getName().equals("main")) {
                try {
                    System.out.printf("=> Method: %s%n", method.getName());
                    method.invoke(null);
                    sleep(waitBetweenCalls);
                    System.out.printf("=============%n");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Runnable getPrintingRunnable(final int order) {
        return () -> {
            System.out.printf("Runnable: %s, in thread: %s%n", order, Thread.currentThread().getName());
        };
    }

    public static Runnable getRunnable(final int id) {
        return getRunnable(id, 100);
    }

    public static Runnable getRunnable(final int id, final int sleepPeriod) {
        return () -> {
            printThreadName();
            sleep(sleepPeriod);
            System.out.printf("Runnable: %s, in thread: %s%n", id, Thread.currentThread().getName());
        };
    }

    public static Callable<String> getCallable(final int order) {
        return getCallable(order, 100);
    }

    public static Callable<String> getCallable(final int order, final long sleepPeriod) {
        return () -> {
            printThreadName();
            sleep(sleepPeriod);
            return String.format("Callable result: %s, in thread: %s", order, Thread.currentThread().getName());
        };
    }
}
