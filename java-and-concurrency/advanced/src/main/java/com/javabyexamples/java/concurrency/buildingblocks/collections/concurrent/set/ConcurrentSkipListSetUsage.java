package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListSetUsage {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadedAccess multiThreadedAccess = new MultiThreadedAccess();
        multiThreadedAccess.doInsert(new ConcurrentSkipListSet<>());
        multiThreadedAccess.doIterate(new ConcurrentSkipListSet<>());
    }
}
