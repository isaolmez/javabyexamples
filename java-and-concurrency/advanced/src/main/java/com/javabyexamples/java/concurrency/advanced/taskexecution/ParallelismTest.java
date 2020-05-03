package com.javabyexamples.java.concurrency.advanced.taskexecution;

import com.javabyexamples.java.concurrency.advanced.common.StopWatch;
import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ParallelismTest {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = new ParallelismTest().getRandomList(100000);
        new ParallelismTest().run(1, 1000, numbers);
        new ParallelismTest().run(2, 1000, numbers);
        new ParallelismTest().run(4, 1000, numbers);
        new ParallelismTest().run(8, 1000, numbers);
        new ParallelismTest().run(16, 1000, numbers);
        new ParallelismTest().run(32, 1000, numbers);
        new ParallelismTest().run(64, 1000, numbers);
        new ParallelismTest().run(128, 1000, numbers);
        new ParallelismTest().run(256, 1000, numbers);
        new ParallelismTest().run(512, 1000, numbers);
        new ParallelismTest().run(1024, 1000, numbers);
    }

    public void run(int threadCount, int taskCount, List<Integer> numbers) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Runner runner = Runner.builder().threadCount(threadCount)
          .addAction()
          .action(() -> Collections.sort(new ArrayList<>(numbers)))
          .executionCount(taskCount)
          .added()
          .build();
        runner.run();
        System.out.printf("Thread count: %s, Time: %s%n", threadCount, stopWatch.elapsedTime());
    }

    public List<Integer> getRandomList(int size) {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(random.nextInt(size));
        }

        return result;
    }
}
