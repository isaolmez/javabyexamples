package com.javabyexamples.java.concurrency.composing.toexisting;

import com.javabyexamples.java.concurrency.common.runner.Runner;
import com.javabyexamples.java.concurrency.composing.toexisting.differentlocks.DifferentLockCollectionHelper;
import com.javabyexamples.java.concurrency.composing.toexisting.samelock.SameLockCollectionHelper;

public class Main {

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(DifferentLockCollectionHelper.class.getName());
        for (int i = 0; i < 100; i++) {
            CollectionHelper differentLock = new DifferentLockCollectionHelper();
            run(differentLock);
        }

        System.out.println(SameLockCollectionHelper.class.getName());
        for (int i = 0; i < 100; i++) {
            CollectionHelper sameLock = new SameLockCollectionHelper();
            run(sameLock);
        }
    }

    public static void run(CollectionHelper collectionHelper) throws InterruptedException {
        Runner runner = Runner.builder()
          .threadCount(THREAD_COUNT)
          .addAction()
          .executionCount(THREAD_COUNT / 2)
          .action(() -> {
              String value = String.valueOf(System.currentTimeMillis() / 100);
              collectionHelper.putIfAbsent(value);
          })
          .added()
          .addAction()
          .executionCount(THREAD_COUNT / 2)
          .action(() -> {
              String value = String.valueOf(System.currentTimeMillis() / 100);
              synchronized (collectionHelper.getList()) {
                  if (!collectionHelper.getList().contains(value)) {
                      collectionHelper.getList().add(value);
                  }
              }
          })
          .added()
          .build();
        runner.run();
        System.out.println("Duplicates: " + collectionHelper.getDuplicates());
    }
}
