package com.javabyexamples.java.core.environmentvariable;

import java.util.Map;
import java.util.Properties;

public class EnvironmentVariableOperations {

    public String get(String key) {
        return System.getenv(key);
    }

    public Map<String, String> getAll() {
        return System.getenv();
    }

    public void set(String key, String value) {
        // Not supported.
    }

    public void remove(String key) {
        // Not supported
    }

    public void setAll(Properties properties) {
        // Not supported
    }
}
