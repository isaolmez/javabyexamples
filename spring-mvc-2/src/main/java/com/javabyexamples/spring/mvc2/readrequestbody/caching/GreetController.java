package com.javabyexamples.spring.mvc2.readrequestbody.caching;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.ContentCachingRequestWrapper;

@RestController
public class GreetController {

    @PostMapping("/greet")
    public String greet(@RequestBody String name, HttpServletRequest request) {
        ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
        String requestBody = new String(requestWrapper.getContentAsByteArray());
        return "Greetings " + requestBody;
    }
}
