package com.javabyexamples.java.concurrency.cancellation.exceptionhandling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WithOverridingAfterExecute {

    public static void main(String[] args) throws InterruptedException {
        final WithOverridingAfterExecute withWrappingTask = new WithOverridingAfterExecute();
        withWrappingTask.executeThenThrowUnchecked();
    }

    public void executeThenThrowUnchecked() throws InterruptedException {
        final ExecutorService executorService = new MonitoringThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>());
        executorService.execute(() -> {
            System.out.println("I will throw RuntimeException now.");
            throw new RuntimeException("Planned exception after execute()");
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }

    public static class MonitoringThreadPoolExecutor extends ThreadPoolExecutor {

        public MonitoringThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
          BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.out.println("Exception message: " + t.getMessage());
        }
    }
}
