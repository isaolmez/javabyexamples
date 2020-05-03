package com.javabyexamples.java.concurrency.advanced.taskexecution;

import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;

public class ThreadSharingTest {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = Runner.builder().threadCount(1)
          .addAction()
          .action(() -> {
              while (true) {
                  System.out.println("Action 1");
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          })
          .executionCount(1)
          .added()
          .addAction()
          .action(() -> {
              while (true) {
                  System.out.println("Action 2");
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          })
          .executionCount(1)
          .added()
          .build();
        runner.run();
    }
}
