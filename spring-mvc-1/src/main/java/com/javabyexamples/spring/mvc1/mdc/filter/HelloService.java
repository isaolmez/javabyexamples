package com.javabyexamples.spring.mvc1.mdc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {

    public String sayHello() {
        log.info("Entered sayHello");
        return "Hello";
    }
}
