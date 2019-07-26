package com.javabyexamples.spring.mvc1.templatelocation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateLocationController {

    @RequestMapping("/welcome")
    public String sayWelcome() {
        return "welcome";
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "hi";
    }

    @RequestMapping("/bye")
    public String sayBye() {
        return "bye";
    }

    @RequestMapping("/farewell")
    public String sayFarewell() {
        return "farewell";
    }
}
