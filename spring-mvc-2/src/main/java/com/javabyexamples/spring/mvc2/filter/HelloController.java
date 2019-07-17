package com.javabyexamples.spring.mvc2.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
