package com.javabyexamples.java.concurrency.threadsafety.atomicity.racecondition.checkthenact;

import com.javabyexamples.java.concurrency.common.runner.Runner;

public class Main {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }

    public void run() throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(THREAD_COUNT)
          .addAction()
          .executionCount(THREAD_COUNT)
          .action(() -> LazyInit.get())
          .added()
          .addAction()
          .executionCount(THREAD_COUNT)
          .action(() -> LazyInitWithSynchronized.get())
          .added()
          .build();
        runner.run();
        System.out.println(LazyInit.getInitializationCount());
        System.out.println(LazyInitWithSynchronized.getInitializationCount());
    }
}
