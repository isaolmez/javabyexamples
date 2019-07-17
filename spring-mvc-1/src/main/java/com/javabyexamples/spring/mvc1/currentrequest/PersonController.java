package com.javabyexamples.spring.mvc1.currentrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final ExceptionHelperService exceptionHelperService;

    @Autowired
    public PersonController(ExceptionHelperService exceptionHelperService) {
        this.exceptionHelperService = exceptionHelperService;
    }

    @PostMapping("/person")
    public String person(@RequestBody Person person) {
        throw new RuntimeException("Planned");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException() {
        System.out.println(exceptionHelperService.getAndProcessFromThreadLocal());
        System.out.println(exceptionHelperService.getAndProcessPersonFromRequestContextHolder());
        System.out.println(exceptionHelperService.getAndProcessPersonFromRequestScope());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
    }
}
