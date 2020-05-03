package com.javabyexamples.java.concurrency.advanced.threadsafety.atomicity.racecondition.checkthenact;

import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;

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
          .build();
        runner.run();
        System.out.println(LazyInit.getInitializationCount());
    }
}
