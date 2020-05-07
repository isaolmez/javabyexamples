package com.javabyexamples.java.concurrency.sharing.visibility.longvisibility;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREAD_COUNT = 100;

    private final CountDownLatch start = new CountDownLatch(1);
    private final LongHolder longHolder;

    public Main(LongHolder longHolder) {
        this.longHolder = longHolder;
    }

    public void run() {
        final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Reader());
            executorService.execute(new Writer());
        }

        start.countDown(); // Let all start

        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public static void main(String[] args) {
        System.out.println("Using NoVisibilityLongHolder");
        Main main = new Main(new NoVisibilityLongHolder());
        main.run();

//        System.out.println("Using LongHolderWithVolatile");
//        final Main main = new Main(new LongHolderWithVolatile());
//        main.run();

//        System.out.println("Using LongHolderWithSynchronized");
//        final Main main = new Main(new LongHolderWithSynchronized());
//        main.run();
    }

    public class Reader implements Runnable {

        @Override
        public void run() {
            try {
                start.await();

                final long counter = longHolder.getCounter();
                if (counter != 0 && counter != Long.MAX_VALUE) {
                    System.out.println("Long visibility error!");
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

                longHolder.alternate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
