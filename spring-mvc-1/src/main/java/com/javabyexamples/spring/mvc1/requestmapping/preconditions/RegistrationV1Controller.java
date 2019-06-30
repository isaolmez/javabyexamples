package com.javabyexamples.spring.mvc1.requestmapping.preconditions;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(params = {"version=1"})
public class RegistrationV1Controller {

    public static final String RESULT = "OK";

    @RequestMapping(value = "/register", params = {"source", "department=engineering"})
    public String registerForEngineering(@RequestParam("name") String name) {
        return RESULT;
    }

    @RequestMapping(value = "/register", params = {"source=web", "department=marketing"})
    public String registerForMarketing(@RequestParam("name") String name) {
        return RESULT;
    }

    @RequestMapping(value = "/enroll", headers = {"!X-Forwarded", "Registration-Key"})
    public String enroll(@RequestParam("name") String name) {
        return RESULT;
    }
}
