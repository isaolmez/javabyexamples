package com.javabyexamples.java.concurrency.executors.custom;

import java.util.concurrent.Executor;

public class DirectExecutor implements Executor {

    public static void main(String[] args) {
        Executor executor = new DirectExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Simple task completed!");
            }
        });
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
