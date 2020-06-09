package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetUsage {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadedAccess multiThreadedAccess = new MultiThreadedAccess();
        multiThreadedAccess.doInsert(new CopyOnWriteArraySet<>());
        multiThreadedAccess.doIterate(new CopyOnWriteArraySet<>());
    }
}
