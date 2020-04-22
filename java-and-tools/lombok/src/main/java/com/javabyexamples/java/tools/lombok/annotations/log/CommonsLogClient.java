package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class CommonsLogClient {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
