package com.javabyexamples.spring.mvc1.async;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {

    private final TaskExecutor taskExecutor;

    public AsyncController(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("callable")
    public Callable<String> callable() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep(100);
                return "Hello";
            }
        };
    }

    @GetMapping("deferredResult")
    public DeferredResult<String> deferredResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        taskExecutor.execute(() -> {
            sleep(100);
            deferredResult.setResult("Hello");
        });
        return deferredResult;
    }

    @GetMapping("completableFuture")
    public CompletableFuture<String> completableFuture() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "Hello";
        }, taskExecutor);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}
