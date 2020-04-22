package com.javabyexamples.java.tools.lombok.annotations.log;

import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class JBossLogClient {

    public static void main(String[] args) {
        log.error("Error occurred", new RuntimeException("Planned"));
    }
}
