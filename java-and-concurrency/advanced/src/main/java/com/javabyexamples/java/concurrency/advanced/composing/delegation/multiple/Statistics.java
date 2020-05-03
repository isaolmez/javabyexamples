package com.javabyexamples.java.concurrency.advanced.composing.delegation.multiple;

public interface Statistics {

    void update();

    boolean invariantHolds();

    void print();
}
