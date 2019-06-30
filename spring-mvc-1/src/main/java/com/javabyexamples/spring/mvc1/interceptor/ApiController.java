package com.javabyexamples.spring.mvc1.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("/stats")
    public String stats() {
        System.out.println("stats()");
        return "stats";
    }
}
