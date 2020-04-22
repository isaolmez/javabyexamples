package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jClient {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
