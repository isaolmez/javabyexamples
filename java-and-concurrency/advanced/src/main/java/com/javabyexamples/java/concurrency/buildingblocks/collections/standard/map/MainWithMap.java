package com.javabyexamples.java.concurrency.buildingblocks.collections.standard.map;

import com.javabyexamples.java.concurrency.common.annotation.NotThreadSafe;
import com.javabyexamples.java.concurrency.common.runner.Runner;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class MainWithMap {

    public static void main(String[] args) throws InterruptedException {
        new MainWithMap().run(new HashMap<>());
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
