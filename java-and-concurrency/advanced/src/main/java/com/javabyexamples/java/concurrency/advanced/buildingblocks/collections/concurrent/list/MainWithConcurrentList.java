package com.javabyexamples.java.concurrency.advanced.buildingblocks.collections.concurrent.list;

import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class MainWithConcurrentList {

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            MainWithConcurrentList main = new MainWithConcurrentList();
            main.run(new CopyOnWriteArrayList<>());
        }
    }

    public void run(List<Integer> values) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(1000)
          .addAction()
          .executionCount(1000)
          .action(() -> {
              values.add(count.getAndIncrement());
          })
          .added()
          .build();
        runner.run();
        System.out.printf("Counter: %s, Size: %s%n", count.get(), values.size());
    }
}
