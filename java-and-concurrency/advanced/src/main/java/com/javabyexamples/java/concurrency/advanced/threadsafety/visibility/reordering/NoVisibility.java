package com.javabyexamples.java.concurrency.advanced.threadsafety.visibility.reordering;

public class NoVisibility {

    private boolean ready;

    private int number;

    // TODO Could not reproduce the effect of partial initialization. Though left the code here
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new NoVisibility().call();
        }
    }

    public void call() {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

    private class ReaderThread extends Thread {

        public void run() {
            while (!ready) {
                Thread.yield();
            }

            if (number != 42) {
                System.out.println("Reordering strikes!");
            }
        }
    }
} 
