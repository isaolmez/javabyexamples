package com.javabyexamples.java.concurrency.advanced.buildingblocks.collections.standard.list;

import com.javabyexamples.java.concurrency.advanced.common.annotation.NotThreadSafe;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class MainWithList {

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            MainWithList main = new MainWithList();
            main.run(new ArrayList<>());
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
