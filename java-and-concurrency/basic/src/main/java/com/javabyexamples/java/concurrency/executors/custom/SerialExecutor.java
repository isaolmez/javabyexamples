package com.javabyexamples.java.concurrency.executors.custom;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SerialExecutor implements Executor {

    private Executor executor;
    private Queue<Runnable> taskQueue = new LinkedList<>();
    private Runnable active = null;

    public static void main(String[] args) {
        Executor executor = new SerialExecutor(Executors.newFixedThreadPool(5));
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task completed!");
            }
        });
    }

    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        taskQueue.offer(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
            }
        });

        if (active == null) {
            scheduleNext();
        }
    }

    private void scheduleNext() {
        if ((active = taskQueue.poll()) != null) {
            executor.execute(active);
        }
    }
}
