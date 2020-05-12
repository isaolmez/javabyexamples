package com.javabyexamples.java.concurrency.threadpool;

import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) {
        new FutureTaskTest().run();
    }

    public void run() {
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.printf("Thread: %s%n", Thread.currentThread().getName());
            return "Runs on main thread!";
        });
        task.run();
    }
}
