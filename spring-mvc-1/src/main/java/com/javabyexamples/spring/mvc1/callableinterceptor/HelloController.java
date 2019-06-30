package com.javabyexamples.spring.mvc1.callableinterceptor;

import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public Callable<String> hello() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Callable has started.");
                return "Hello";
            }
        };
    }
}
