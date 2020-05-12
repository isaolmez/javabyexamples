package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownByThreadCount {

    public static void main(String[] args) {
        new ShutdownByThreadCount().runWithPool();
//        new ThreadCount().runWithSingleThreadedPool();
//        new ThreadCount().runWithThread();
//        new ThreadCount().runWithDaemonThread();
    }

    public void runWithPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new InfiniteRunner());

        System.out.println("Exiting main thread!");
    }

    public void runWithSingleThreadedPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new InfiniteRunner());

        System.out.println("Exiting main thread!");
    }

    public void runWithThread() {
        new Thread(new InfiniteRunner()).start();

        System.out.println("Exiting main thread!");
    }

    public void runWithDaemonThread() {
        Thread daemonThread = new Thread(new InfiniteRunner());
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println("Exiting main thread!");
    }
}
