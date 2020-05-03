package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache;

public interface Computable<A, V> {

    V compute(A arg);
}
