package com.javabyexamples.java.concurrency.advanced.taskexecution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JvmTerminationTest {

    public static void main(String[] args) {
//        new JvmTerminationTest().runWithPool();
//        new JvmTerminationTest().runWithSingleThreadedPool();
//        new JvmTerminationTest().runWithThread();
        new JvmTerminationTest().runWithDaemonThread();
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

    private static class InfiniteRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
