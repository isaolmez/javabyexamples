package com.javabyexamples.spring.mvc1.responsestatus;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseStatusRestController {

    @GetMapping("/status")
    public String status() {
        return "Done";
    }

    @PostMapping("/statusPost")
    public String statusPost() {
        return "Done";
    }

    @PutMapping("/statusPut")
    public String statusPut() {
        return "Done";
    }

    @DeleteMapping("/statusDelete")
    public String statusDelete() {
        return "Done";
    }

    @PatchMapping("/statusPatch")
    public String statusPatch() {
        return "Done";
    }

    @PostMapping("/statusWithResponseEntity")
    public ResponseEntity<String> statusWithResponseEntity() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Done");
    }

    @PostMapping("/statusWithResponse")
    public String statusWithResponse(HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return "Done";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/statusWithAnnotation")
    public String statusWithAnnotation() {
        return "Done";
    }
}
