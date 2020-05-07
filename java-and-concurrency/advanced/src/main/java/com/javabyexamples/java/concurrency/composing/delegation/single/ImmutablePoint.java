package com.javabyexamples.java.concurrency.composing.delegation.single;

import com.javabyexamples.java.concurrency.common.annotation.Immutable;

@Immutable
public class ImmutablePoint {

    public final int x;
    public final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
