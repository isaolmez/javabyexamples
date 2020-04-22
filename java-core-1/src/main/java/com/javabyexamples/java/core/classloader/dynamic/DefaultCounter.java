package com.javabyexamples.java.core.classloader.dynamic;

public class DefaultCounter implements Counter {

    private int counter;

    public String message() {
        return "Version 2";
    }

    public int plusPlus() {
        return counter++;
    }

    public int counter() {
        return counter;
    }
}
