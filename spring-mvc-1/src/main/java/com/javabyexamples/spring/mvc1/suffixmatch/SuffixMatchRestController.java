package com.javabyexamples.spring.mvc1.suffixmatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuffixMatchRestController {

    @GetMapping("/bye")
    public String hello() {
        return "bye";
    }
}
