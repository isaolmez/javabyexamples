package com.javabyexamples.java.concurrency.composing.delegation.single;

import com.javabyexamples.java.concurrency.common.annotation.GuardedBy;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;

@ThreadSafe
public class SafeMutablePoint {

    @GuardedBy("this")
    private int x, y;

    private SafeMutablePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafeMutablePoint(SafeMutablePoint p) {
        this(p.get());
    }

    public SafeMutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
} 
