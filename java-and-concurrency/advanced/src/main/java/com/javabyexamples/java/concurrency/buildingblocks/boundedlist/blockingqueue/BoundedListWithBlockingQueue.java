package com.javabyexamples.java.concurrency.buildingblocks.boundedlist.blockingqueue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BoundedListWithBlockingQueue<T> {

    private final BlockingQueue<Object> blockingQueue;
    private final List<T> list;

    public BoundedListWithBlockingQueue(List<T> list, int capacity) {
        this.list = Collections.synchronizedList(list);
        blockingQueue = new LinkedBlockingQueue<>(capacity);
    }

    public void add(T element) throws InterruptedException {
        blockingQueue.put(new Object());
        boolean added = false;
        try {
            added = list.add(element);
        } finally {
            if (!added) {
                blockingQueue.poll();
            }
        }
    }

    public boolean remove(T element) {
        boolean removed = list.remove(element);
        if (removed) {
            blockingQueue.poll();
        }

        return removed;
    }

    public int size() {
        return list.size();
    }
}
