package com.javabyexamples.spring.mvc1.suffixmatch.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
