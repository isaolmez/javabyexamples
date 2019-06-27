package com.javabyexamples.spring.mvc1.suffixmatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuffixMatchController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
