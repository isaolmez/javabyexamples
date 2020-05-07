package com.javabyexamples.java.concurrency.composing.instanceconfinement;

import com.javabyexamples.java.concurrency.common.annotation.Mutable;

@Mutable
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
} 
