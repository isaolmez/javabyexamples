package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.flogger.Flogger;

@Flogger
public class FLoggerClient {

    public static void main(String[] args) {
        log.atSevere().withCause(new RuntimeException("Planned")).log("Error occurred");
    }
}
