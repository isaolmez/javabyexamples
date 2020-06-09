package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasedSetUsage {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadedAccess multiThreadedAccess = new MultiThreadedAccess();
        multiThreadedAccess.doInsert(ConcurrentHashMap.newKeySet());
        multiThreadedAccess.doIterate(ConcurrentHashMap.newKeySet());
    }
}
