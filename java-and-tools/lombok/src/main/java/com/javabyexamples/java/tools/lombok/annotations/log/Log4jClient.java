package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.log4j.Log4j;

@Log4j
public class Log4jClient {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
