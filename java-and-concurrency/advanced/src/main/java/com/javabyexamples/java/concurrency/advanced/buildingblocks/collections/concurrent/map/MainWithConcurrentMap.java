package com.javabyexamples.java.concurrency.advanced.buildingblocks.collections.concurrent.map;

import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class MainWithConcurrentMap {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new MainWithConcurrentMap().run(new ConcurrentHashMap<>());
        }
    }

    public void run(Map<String, Integer> map) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Runner runner = Runner.builder()
          .threadCount(1000)
          .addAction()
          .executionCount(1000)
          .action(() -> map.put("a", counter.getAndIncrement()))
          .added()
          .build();
        runner.run();
        System.out.printf("Counter: %s, Size: %s%n", counter.get(), map.size());
    }
}
