package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.slf4j.XSlf4j;

@XSlf4j
public class XSlf4jClient {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
