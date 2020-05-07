package com.javabyexamples.java.concurrency.buildingblocks.cache;

public interface Computable<A, V> {

    V compute(A arg);
}
