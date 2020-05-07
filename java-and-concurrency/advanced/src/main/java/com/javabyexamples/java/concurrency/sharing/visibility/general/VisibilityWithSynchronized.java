package com.javabyexamples.java.concurrency.sharing.visibility.general;

import java.util.concurrent.TimeUnit;

public class VisibilityWithSynchronized {

    private boolean ready;

    public void perform() throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while (!isReady()) {
                i++;
            }

            System.out.println("Started.");
        }).start();

        TimeUnit.MILLISECONDS.sleep(100);
        setReady(true);
    }

    public synchronized boolean isReady() {
        return ready;
    }

    public synchronized void setReady(boolean ready) {
        this.ready = ready;
    }

    public static void main(String[] args) throws InterruptedException {
        new VisibilityWithSynchronized().perform();
    }
}
