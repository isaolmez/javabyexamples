package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.semaphore;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BoundedListWithSemaphore<T> {

    private final Semaphore semaphore;
    private List<T> list;

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

    public void remove(T element) {
        boolean removed = list.remove(element);
        if (removed) {
            semaphore.release();
        }
    }

    public int size() {
        return list.size();
    }
}
