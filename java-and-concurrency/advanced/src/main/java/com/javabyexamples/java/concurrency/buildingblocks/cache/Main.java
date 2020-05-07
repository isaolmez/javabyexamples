package com.javabyexamples.java.concurrency.buildingblocks.cache;

import com.javabyexamples.java.concurrency.buildingblocks.cache.example1.CacheWithSynchronized;
import com.javabyexamples.java.concurrency.buildingblocks.cache.example2.CacheWithConcurrentMap;
import com.javabyexamples.java.concurrency.buildingblocks.cache.example3.CacheWithConcurrentMapAndFuture;
import com.javabyexamples.java.concurrency.common.runner.Runner;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Synchronized HashMap");
        new Main().run(new CacheWithSynchronized<>(new ExpensiveFunction()));

        System.out.println("ConcurrentHashMap");
        new Main().run(new CacheWithConcurrentMap<>(new ExpensiveFunction()));

        System.out.println("ConcurrentHashMap with FutureTask");
        new Main().run(new CacheWithConcurrentMapAndFuture<>(new ExpensiveFunction()));
    }

    public void run(ComputableCache<String, BigInteger> computableCache) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(1000)
          .addAction()
          .action(() -> computableCache.compute("100"))
          .executionCount(1000)
          .added()
          .build();
        runner.run();
        System.out.println(computableCache.getCache());
        System.out.println(computableCache.getPutCount());
    }
}
