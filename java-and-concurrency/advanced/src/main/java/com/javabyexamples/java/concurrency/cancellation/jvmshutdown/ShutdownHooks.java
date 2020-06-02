package com.javabyexamples.java.concurrency.cancellation.jvmshutdown;

import java.lang.Thread.UncaughtExceptionHandler;

public class ShutdownHooks {

    public static void main(String[] args) {
//        new ShutdownHooks().runHooksOnZeroThread();

        new ShutdownHooks().runHooksOnExit();

//        new ShutdownHooks().skipHooksOnHalt();

//        new ShutdownHooks().exceptionHandlingInHooks();
    }

    public void runHooksOnZeroThread() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);
    }

    public void runHooksOnExit() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        System.out.println("Exiting...");
        System.exit(0); // Runtime.getRuntime().exit(status);
    }

    public void skipHooksOnHalt() {
        final Thread firstHook = new Thread(() -> System.out.println("First hook."));
        Runtime.getRuntime().addShutdownHook(firstHook);

        final Thread secondHook = new Thread(() -> System.out.println("Second hook."));
        Runtime.getRuntime().addShutdownHook(secondHook);

        Runtime.getRuntime().halt(1);
    }

    public void exceptionHandlingInHooks() {
        final Thread hook = new Thread(() -> {
            throw new RuntimeException("Planned");
        });
        hook.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Exception: " + e.getMessage());
            }
        });
        Runtime.getRuntime().addShutdownHook(hook);

        System.exit(0);
    }
}
