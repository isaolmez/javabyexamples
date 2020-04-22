package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Log4j2Client {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
