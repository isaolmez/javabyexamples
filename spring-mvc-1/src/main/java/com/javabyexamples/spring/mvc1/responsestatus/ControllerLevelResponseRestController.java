package com.javabyexamples.spring.mvc1.responsestatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(HttpStatus.ACCEPTED)
@RestController
@RequestMapping("/controller")
public class ControllerLevelResponseRestController {

    @PostMapping("/status")
    public String status() {
        return "Done";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/statusOverride")
    public String statusWithAnnotation() {
        return "Done";
    }

    @PostMapping("/statusWithThrowing")
    public String statusWithThrowing() {
        throw new RuntimeException("Planned");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException e) {
    }
}
