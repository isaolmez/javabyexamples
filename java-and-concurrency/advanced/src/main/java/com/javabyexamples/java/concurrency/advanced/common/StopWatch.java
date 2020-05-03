package com.javabyexamples.java.concurrency.advanced.common;

public class StopWatch {

    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long elapsedTime() {
        if (start == 0) {
            return 0;
        }

        return System.currentTimeMillis() - start;
    }
}
