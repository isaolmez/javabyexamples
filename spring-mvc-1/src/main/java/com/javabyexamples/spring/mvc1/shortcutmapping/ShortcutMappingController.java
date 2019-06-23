package com.javabyexamples.spring.mvc1.shortcutmapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortcutMappingController {

    @RequestMapping(value = "/greet", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String greet(@RequestParam("name") String name) {
        return "Hi " + name;
    }

    @GetMapping(value = "/greetGet", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetGet(@RequestParam("name") String name) {
        return "Hi " + name;
    }

    @PostMapping("/greetPost")
    public String greetPost(@RequestParam("name") String name) {
        return "Hi " + name;
    }

    @DeleteMapping("/greetDelete")
    public String greetDelete(@RequestParam("name") String name) {
        return "Hi " + name;
    }

    @PutMapping("/greetPut")
    public String greetPut(@RequestParam("name") String name) {
        return "Hi " + name;
    }

    @PatchMapping("/greetPatch")
    public String greetPatch(@RequestParam("name") String name) {
        return "Hi " + name;
    }
}
