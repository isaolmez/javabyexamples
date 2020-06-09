package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.HashSet;

public class HashSetUsage {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadedAccess multiThreadedAccess = new MultiThreadedAccess();
        multiThreadedAccess.doInsert(new HashSet<>());
        multiThreadedAccess.doIterate(new HashSet<>());
    }
}
