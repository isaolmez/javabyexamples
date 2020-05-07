package com.javabyexamples.java.concurrency.sharing.visibility.general;

import java.util.concurrent.TimeUnit;

public class NoVisibility {

    private boolean ready;

    public static void main(String[] args) throws InterruptedException {
        new NoVisibility().perform();
    }

    public void perform() throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while (!ready) {
                i++;
            }

            System.out.println("Started.");
        }).start();

        TimeUnit.MILLISECONDS.sleep(100);
        ready = true;
    }
}
