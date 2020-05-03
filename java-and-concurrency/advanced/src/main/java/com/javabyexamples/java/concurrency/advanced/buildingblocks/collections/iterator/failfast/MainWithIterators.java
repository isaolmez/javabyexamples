package com.javabyexamples.java.concurrency.advanced.buildingblocks.collections.iterator.failfast;

import com.javabyexamples.java.concurrency.advanced.common.runner.Runner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainWithIterators {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Fail-fast standard ArrayList");
        run(new ArrayList<>());

        Thread.sleep(1000);
        System.out.println("Fail-fast synchronized ArrayList");
        run(Collections.synchronizedList(new ArrayList<>()));

        Thread.sleep(1000);
        System.out.println("Fail-safe CopyOnWriteArrayList: Takes a snapshot of the list when creating iterator");
        run(new CopyOnWriteArrayList<>());
    }

    public static void run(List<String> values) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(1000)
          .addAction()
          .executionCount(500)
          .action(() -> {
              values.add("a");
          })
          .added()
          .addAction()
          .executionCount(500)
          .action(() -> {
              for (String value : values) {
                  System.out.print("");
                  ;
              }
          })
          .added()
          .build();
        runner.run();
    }
}
