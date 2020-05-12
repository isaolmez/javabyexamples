package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

public class ShutdownHooks {

    public static void main(String[] args) {
//        new ShutdownHooks().runHooksOnZeroThread();

//        new ShutdownHooks().runHooksOnExit();

//        new ShutdownHooks().skipHooksOnHalt();

        new ShutdownHooks().skipHooksOnCtrlC();
    }

    public void runHooksOnZeroThread() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        System.out.println("Exiting main thread!");
    }

    public void runHooksOnExit() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        System.out.println("Exiting main thread!");

        System.exit(0); // Runtime.getRuntime().exit(status);
    }

    public void skipHooksOnHalt() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        System.out.println("Exiting main thread!");

        Runtime.getRuntime().halt(1);
    }

    public void skipHooksOnCtrlC() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        System.out.println("Exiting main thread!");

        new Thread(new InfiniteRunner()).start();
    }
}
