package com.javabyexamples.java.concurrency.buildingblocks.synchronizers.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDetails {

    public static void main(String[] args) throws InterruptedException {
        final ConditionDetails conditionDetails = new ConditionDetails();
//        conditionDetails.awaitOnCondition();
//        conditionDetails.awaitOnConditionWithoutAcquisition();
//        conditionDetails.signalOnCondition();
//        conditionDetails.awaitUninterruptiblyOnCondition();
        conditionDetails.timedAwaitOnCondition();
    }

    private final Lock listLock = new ReentrantLock();
    private final Condition notEmpty = listLock.newCondition();

    public void awaitOnCondition() throws InterruptedException {
        listLock.lock();
        try {
            while (isEmpty()) {
                System.out.println("I will wait now");
                notEmpty.await();
            }

            // Do work.
        } finally {
            listLock.unlock();
        }
    }

    public void signalOnCondition() {
        listLock.lock();
        try {
            // Do work.

            System.out.println("I will signal all.");
            notEmpty.signalAll();
        } finally {
            listLock.unlock();
        }
    }

    public void awaitOnConditionWithoutAcquisition() throws InterruptedException {
        notEmpty.await(); // throws IllegalMonitorStateException
    }

    private boolean isEmpty() {
        return true;
    }

    public void awaitUninterruptiblyOnCondition() {
        listLock.lock();
        try {
            while (isEmpty()) {
                System.out.println("I will wait ignoring interrupts");
                notEmpty.awaitUninterruptibly();
            }

            // Do work.
        } finally {
            listLock.unlock();
        }
    }

    public void timedAwaitOnCondition() throws InterruptedException {
        listLock.lock();
        try {
            while (isEmpty()) {
                System.out.println("I can be back in one second");
                notEmpty.await(1, TimeUnit.SECONDS);
            }

            // Do work.
        } finally {
            listLock.unlock();
        }
    }
}
