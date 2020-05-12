package com.javabyexamples.java.concurrency.cancellation.canceltask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancelNonInterruptibleTask {

    public static void main(String[] args) throws Exception {
        final CancelNonInterruptibleTask cancellation = new CancelNonInterruptibleTask();

        cancellation.startAndCancel();
    }

    public void startAndCancel() throws Exception {
        final ExecutorService executorService = new CancellingThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
          new LinkedBlockingQueue<>());

        final ServerSocket socket = new ServerSocket(9090);
        final Future<?> future = executorService.submit(new ServerSocketTask(socket) {
            @Override
            public String call() throws Exception {
                socket.accept();
                return "done";
            }
        });

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        future.cancel(true);

        executorService.shutdown();
    }

    public static class CancellingThreadPoolExecutor extends ThreadPoolExecutor {

        public CancellingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
          BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
            if (callable instanceof CustomCancellingTask) {
                return ((CustomCancellingTask<T>) callable).newFutureTask();
            } else {
                return super.newTaskFor(callable);
            }
        }
    }

    public interface CustomCancellingTask<T> extends Callable<T> {

        void cancel();

        default RunnableFuture<T> newFutureTask() {
            return new FutureTask<T>(this) {
                public boolean cancel(boolean mayInterruptIfRunning) {
                    try {
                        CustomCancellingTask.this.cancel();
                    } finally {
                        return super.cancel(mayInterruptIfRunning);
                    }
                }
            };
        }
    }

    public abstract class ServerSocketTask implements CustomCancellingTask<String> {

        private final ServerSocket socket;

        protected ServerSocketTask(ServerSocket socket) {
            this.socket = socket;
        }

        @Override
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error occurred.");
            }
        }
    }
}
