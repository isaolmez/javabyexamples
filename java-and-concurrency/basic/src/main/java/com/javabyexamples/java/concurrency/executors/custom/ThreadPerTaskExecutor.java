package com.javabyexamples.java.concurrency.executors.custom;

import java.util.concurrent.Executor;

public class ThreadPerTaskExecutor implements Executor {

    public static void main(String[] args) {
        Executor executor = new ThreadPerTaskExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task completed!");
            }
        });
    }

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
