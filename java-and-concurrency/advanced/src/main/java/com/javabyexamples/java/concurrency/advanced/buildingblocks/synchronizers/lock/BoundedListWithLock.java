package com.javabyexamples.java.concurrency.advanced.buildingblocks.synchronizers.lock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedListWithLock<T> {

    private final Lock lock = new ReentrantLock();
    private final Condition notAtCapacity = lock.newCondition();
    private List<T> list;
    private int capacity;

    public BoundedListWithLock(List<T> list, int capacity) {
        this.list = Collections.synchronizedList(list);
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

    public void remove(T element) {
        lock.lock();
        try {
            list.remove(element);
            notAtCapacity.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
