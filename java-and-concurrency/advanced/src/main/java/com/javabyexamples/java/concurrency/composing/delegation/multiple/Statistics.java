package com.javabyexamples.java.concurrency.composing.delegation.multiple;

public interface Statistics {

    void update();

    boolean invariantHolds();

    void print();
}
