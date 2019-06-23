package com.javabyexamples.spring.mvc1.responsestatus;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseStatusRestController {

    @GetMapping("/status")
    public String status() {
        return "Hello";
    }

    @PostMapping("/statusPost")
    public String statusPost() {
        return "Hello";
    }

    @PostMapping("/statusWithResponseEntity")
    public ResponseEntity<String> statusWithResponseEntity() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello");
    }

    @PostMapping("/statusWithResponse")
    public String statusWithResponse(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return "Hello";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/statusWithAnnotation")
    public String statusWithAnnotation() {
        return "Hello";
    }
}
