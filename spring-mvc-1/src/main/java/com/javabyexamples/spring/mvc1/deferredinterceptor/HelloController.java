package com.javabyexamples.spring.mvc1.deferredinterceptor;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class HelloController {

    private final TaskExecutor taskExecutor;

    public HelloController(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("hello")
    public DeferredResult<String> hello() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        taskExecutor.execute(() -> {
            delay(100);
            System.out.println("Setting deferred result");
            deferredResult.setResult("Hello");
        });
        return deferredResult;
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Error occurred.");
        }
    }
}
