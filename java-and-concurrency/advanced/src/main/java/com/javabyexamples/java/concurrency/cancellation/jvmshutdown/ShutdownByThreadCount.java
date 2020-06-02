package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownByThreadCount {

    public static void main(String[] args) {
        final ShutdownByThreadCount shutdownByThreadCount = new ShutdownByThreadCount();
//        shutdownByThreadCount.runWithPoolWithoutExecutingAnyTask();
//        shutdownByThreadCount.runWithPool();
//        shutdownByThreadCount.runWithSingleThreadedPool();
//        shutdownByThreadCount.runWithThread();
//        shutdownByThreadCount.runWithThreadExecutingInfiniteRunner();
//        shutdownByThreadCount.runWithDaemonThread();
    }

    public void runWithPoolWithoutExecutingAnyTask() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println("Completing the method!");
    }

    public void runWithPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> System.out.println("Hello world!"));

        System.out.println("Completing the method!");
    }

    public void runWithPoolAndInfiniteRunner() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new InfiniteRunner());

        System.out.println("Completing the method!");
    }

    public void runWithSingleThreadedPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println("Hello world!"));

        System.out.println("Completing the method!");
    }

    public void runWithThread() {
        new Thread(() -> System.out.println("Hello world!")).start();

        System.out.println("Completing the method!");
    }

    public void runWithThreadExecutingInfiniteRunner() {
        new Thread(new InfiniteRunner()).start();

        System.out.println("Completing the method!");
    }

    public void runWithDaemonThread() {
        Thread daemonThread = new Thread(new InfiniteRunner());
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println("Completing the method!");
    }
}
