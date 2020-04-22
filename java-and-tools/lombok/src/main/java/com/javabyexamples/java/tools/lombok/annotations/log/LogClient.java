package com.javabyexamples.java.tools.lombok.annotations.log;

import java.util.logging.Level;
import lombok.extern.java.Log;

@Log
public class LogClient {

    public static void main(String[] args) {
        log.log(Level.SEVERE, "Error occurred", new RuntimeException("Planned"));
    }
}
