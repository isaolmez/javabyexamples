package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {

    public BigInteger compute(String arg) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new BigInteger(arg);
    }
}
