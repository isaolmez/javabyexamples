package com.javabyexamples.java.concurrency.advanced.threadsafety.visibility.longdouble;

import com.javabyexamples.java.concurrency.advanced.common.ExecutorUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREAD_COUNT = 10;

    private LongDoubleContainer container = new LongDoubleContainer();

    private CountDownLatch start;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Main().run();
        }
    }

    public void run() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        start = new CountDownLatch(1);

        for (int i = 0; i < 500; i++) {
            executorService.execute(new Reader());
            executorService.execute(new Writer());
        }

        start.countDown();
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public class Reader implements Runnable {

        @Override
        public void run() {
            try {
                start.await();
                // Beware! it prints normal values
                if (container.counter != 0 && container.counter != Long.MAX_VALUE) {
                    System.out.printf("%s: Long visibility error!%n", container.counter);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class Writer implements Runnable {

        @Override
        public void run() {
            try {
                start.await();
                container.bounce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
