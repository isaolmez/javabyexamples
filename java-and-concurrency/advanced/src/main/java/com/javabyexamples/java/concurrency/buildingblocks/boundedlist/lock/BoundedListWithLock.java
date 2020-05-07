package com.javabyexamples.java.concurrency.buildingblocks.boundedlist.lock;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedListWithLock<T> {

    private final Lock lock = new ReentrantLock();
    private final Condition notAtCapacity = lock.newCondition();

    private final List<T> list;
    private final int capacity;

    public BoundedListWithLock(List<T> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    public void add(T element) {
        lock.lock();
        try {
            while (list.size() == capacity) {
                notAtCapacity.await();
            }

            list.add(element);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(T element) {
        lock.lock();
        try {
            final boolean removed = list.remove(element);
            if (removed) {
                notAtCapacity.signalAll();
            }

            return removed;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
