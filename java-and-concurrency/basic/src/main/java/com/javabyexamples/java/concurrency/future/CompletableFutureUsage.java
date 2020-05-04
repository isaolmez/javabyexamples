package com.javabyexamples.java.concurrency.future;

import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.printThreadName;
import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.runStaticMethods;
import static com.javabyexamples.java.concurrency.utils.ConcurrencyUtils.sleep;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureUsage {

    private static final LongRunningOperation LONG_RUNNING = new LongRunningOperation();

    public static void main(String[] args) {
        runStaticMethods(CompletableFutureUsage.class, 1000);
    }

    public void create() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        printStatus(completableFuture);
    }

    public void getWithoutComplete() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        try {
            completableFuture.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }

        printStatus(completableFuture);
    }

    public void complete() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        try {
            completableFuture.complete("Test result");
            String result = completableFuture.get(100, TimeUnit.MILLISECONDS);
            System.out.printf("Result: %s%n", result);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }

        printStatus(completableFuture);
    }

    public void completeExceptionally() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new RuntimeException("Planned exception"));

        printStatus(completableFuture);
    }

    public void runAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> LONG_RUNNING.perform())
          .thenRunAsync(() -> LONG_RUNNING.perform());

        sleep(1000);
        printStatus(completableFuture);
    }

    public void supplyAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture
          .supplyAsync(() -> LONG_RUNNING.performAndReturn());

        sleep(1000);
        printStatus(completableFuture);
    }

    public void whenCompleteAsyncWithSuccess() {
        CompletableFuture<String> completableFuture = CompletableFuture
          .supplyAsync(() -> LONG_RUNNING.performAndReturn())
          .whenCompleteAsync((result, exception) -> {
              printThreadName();
              if (result != null) {
                  System.out.printf("Result: %s%n", result);
              } else {
                  System.out.println("Exception occurred");
              }
          });

        sleep(1000);
        printStatus(completableFuture);
    }

    public void whenCompleteAsyncWithException() {
        CompletableFuture<String> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndThrow)
          .whenCompleteAsync((result, exception) -> {
              printThreadName();
              if (result != null) {
                  System.out.printf("Result: %s%n", result);
              } else {
                  System.out.println("Exception occurred");
              }
          });

        sleep(1000);
        printStatus(completableFuture);
    }

    public void thenAcceptAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndReturn)
          .thenAcceptAsync(System.out::println);

        printStatus(completableFuture);
    }

    public void thenComposeAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndReturn)
          .thenComposeAsync(result -> CompletableFuture.supplyAsync(() -> LONG_RUNNING.performAndReturn(result)))
          .thenAcceptAsync(System.out::println);

        printStatus(completableFuture);
    }

    public void thenCombineAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndReturn)
          .thenCombine(CompletableFuture.supplyAsync(LONG_RUNNING::performAndReturn), (first, second) -> first + second)
          .thenAcceptAsync(System.out::println);

        printStatus(completableFuture);
    }

    public void handle() {
        CompletableFuture<Void> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndThrow)
          .handle((result, exception) -> {
              if (result == null) {
                  return "Unknown";
              }

              return result;
          })
          .thenAcceptAsync(System.out::println);

        printStatus(completableFuture);
    }

    public void exceptionally() {
        CompletableFuture<String> completableFuture = CompletableFuture
          .supplyAsync(LONG_RUNNING::performAndThrow)
          .exceptionally(exception -> "Unknown");

        try {
            System.out.printf("Result: %s%n", completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        printStatus(completableFuture);
    }

    private void printStatus(CompletableFuture<?> completableFuture) {
        System.out.printf("Is done? : %s%n", completableFuture.isDone());
        System.out.printf("Is cancelled? : %s%n", completableFuture.isCancelled());
        System.out.printf("Is completed exceptionally? : %s%n", completableFuture.isCompletedExceptionally());
    }

    private static class LongRunningOperation {

        public String performAndReturn() {
            printThreadName();
            sleep(100);
            return "Test result";
        }

        public String performAndReturn(String input) {
            printThreadName();
            sleep(100);
            return "Test result" + input;
        }

        public void perform() {
            printThreadName();
            sleep(100);
        }

        public String performAndThrow() {
            printThreadName();
            sleep(100);
            throw new RuntimeException("Planned exception");
        }
    }
}
