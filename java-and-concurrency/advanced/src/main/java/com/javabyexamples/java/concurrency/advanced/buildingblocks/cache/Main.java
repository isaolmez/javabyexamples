package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache;

import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example1.ComputableCache1;
import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example2.ComputableCache2;
import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example3.ComputableCache3;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Synchronized HashMap");
        new Main().run(new ComputableCache1<>(new ExpensiveFunction()));

        System.out.println("ConcurrentHashMap");
        new Main().run(new ComputableCache2<>(new ExpensiveFunction()));

        System.out.println("ConcurrentHashMap with FutureTask");
        new Main().run(new ComputableCache3<>(new ExpensiveFunction()));
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
