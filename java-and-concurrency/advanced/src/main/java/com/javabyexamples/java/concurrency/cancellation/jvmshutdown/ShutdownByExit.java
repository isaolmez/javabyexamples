package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

public class ShutdownByExit {

    public static void main(String[] args) {
//        new ShutdownByExit().exit();
        new ShutdownByExit().halt();
    }

    public void exit() {
        new Thread(new InfiniteRunner()).start();

        System.out.println("Exiting main thread!");

        System.exit(0);
    }

    public void halt() {
        new Thread(new InfiniteRunner()).start();

        System.out.println("Halting main thread!");

        System.exit(1);
    }
}
