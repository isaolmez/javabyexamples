package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

public class InfiniteRunner implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted.");
            }
        }
    }
}
