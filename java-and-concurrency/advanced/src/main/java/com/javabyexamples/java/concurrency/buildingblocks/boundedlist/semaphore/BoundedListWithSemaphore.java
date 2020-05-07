package com.javabyexamples.java.concurrency.buildingblocks.boundedlist.semaphore;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BoundedListWithSemaphore<T> {

    private final Semaphore semaphore;
    private final List<T> list;

    public BoundedListWithSemaphore(List<T> list, int capacity) {
        this.list = Collections.synchronizedList(list);
        this.semaphore = new Semaphore(capacity);
    }

    public void add(T element) throws InterruptedException {
        semaphore.acquire();
        boolean added = false;
        try {
            added = list.add(element);
        } finally {
            if (!added) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T element) {
        boolean removed = list.remove(element);
        if (removed) {
            semaphore.release();
        }

        return removed;
    }

    public int size() {
        return list.size();
    }
}
