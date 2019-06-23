package com.javabyexamples.spring.mvc1.restreturnvalues;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnValueRestController {

    @GetMapping("/get")
    public String get() {
        return "Hello";
    }

    @GetMapping("/getNoBody")
    public void getWithNoBody() {
    }

    @GetMapping("/getWithHttpEntity")
    public HttpEntity<String> getWithHttpEntity() {
        return new HttpEntity<>("Hello");
    }

    @GetMapping("/getWithResponseEntity")
    public ResponseEntity<String> getWithResponseEntity() {
        return ResponseEntity.ok().body("Hello");
    }

    @GetMapping("/getHeaders")
    public HttpHeaders getHeaders(@RequestHeader HttpHeaders httpHeaders) {
        HttpHeaders requestHeaders = new HttpHeaders(httpHeaders);
        requestHeaders.add("Additional", "Value");
        return requestHeaders;
    }
}
