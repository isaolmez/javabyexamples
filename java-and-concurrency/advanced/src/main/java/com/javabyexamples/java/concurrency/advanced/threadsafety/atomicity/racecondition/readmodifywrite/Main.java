package com.javabyexamples.java.concurrency.advanced.threadsafety.atomicity.racecondition.readmodifywrite;

import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;

public class Main {

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
//        Incrementor incrementor = new Incrementor1();
//        Incrementor incrementor = new Incrementor2();
//        Incrementor incrementor = new Incrementor3();
        Incrementor incrementor = new Incrementor4();
        for (int i = 0; i < 100; i++) {
            run(incrementor);
            if (incrementor.get() != THREAD_COUNT) {
                System.out.println("RACE CONDITION!!!");
            }

            incrementor.reset();
        }
    }

    public static void run(Incrementor incrementor) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(THREAD_COUNT)
          .addAction()
          .executionCount(THREAD_COUNT)
          .action(() -> incrementor.increment())
          .added()
          .build();
        runner.run();
    }
}
