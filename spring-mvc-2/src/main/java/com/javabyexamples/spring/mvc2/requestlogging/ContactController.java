package com.javabyexamples.spring.mvc2.requestlogging;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @PostMapping("/contact/{name}")
    public String contact(@PathVariable("name") String name, @RequestBody String details) {
        return "Contact details received for: " + name;
    }
}
