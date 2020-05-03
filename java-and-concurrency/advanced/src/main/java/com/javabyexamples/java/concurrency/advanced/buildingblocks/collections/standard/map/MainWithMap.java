package com.javabyexamples.java.concurrency.advanced.buildingblocks.collections.standard.map;

import com.javabyexamples.java.concurrency.advanced.common.annotation.NotThreadSafe;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class MainWithMap {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new MainWithMap().run(new HashMap<>());
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
