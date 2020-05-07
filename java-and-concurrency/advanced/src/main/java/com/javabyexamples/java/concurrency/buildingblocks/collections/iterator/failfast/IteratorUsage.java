package com.javabyexamples.java.concurrency.buildingblocks.collections.iterator.failfast;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import com.javabyexamples.java.concurrency.common.runner.Runner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IteratorUsage {

    public static void main(String[] args) throws InterruptedException {
        final IteratorUsage iteratorUsage = new IteratorUsage();

        System.out.println("Fail-fast standard ArrayList");
        iteratorUsage.run(new ArrayList<>());

        System.out.println("Fail-fast synchronized ArrayList");
        iteratorUsage.run(Collections.synchronizedList(new ArrayList<>()));

        System.out.println("Fail-safe CopyOnWriteArrayList: Iterates on the current snapshot.");
        iteratorUsage.run(new CopyOnWriteArrayList<>());
    }

    public void run(List<String> values) throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    start.await();

                    values.add("a");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                } finally {
                    done.countDown();
                }
            });

            executorService.execute(() -> {
                try {
                    start.await();

                    for (String value : values) {
                        System.out.print("");
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                } finally {
                    done.countDown();
                }
            });
        }

        start.countDown();
        done.await();

        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public void runAdvanced(List<String> values) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(100)
          .addAction()
          .executionCount(50)
          .action(() -> {
              values.add("a");
          })
          .added()
          .addAction()
          .executionCount(50)
          .action(() -> {
              for (String value : values) {
                  System.out.print("");
              }
          })
          .added()
          .build();
        runner.run();
    }
}
