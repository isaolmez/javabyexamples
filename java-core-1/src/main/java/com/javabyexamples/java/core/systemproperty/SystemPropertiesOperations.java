package com.javabyexamples.java.core.systemproperty;

import java.util.Properties;

public class SystemPropertiesOperations {

    public String get(String key) {
        return System.getProperty(key);
    }

    public void set(String key, String value) {
        System.setProperty(key, value);
    }

    public void remove(String key) {
        System.clearProperty(key);
    }

    public Properties getAll() {
        return System.getProperties();
    }

    public void setAll(Properties properties) {
        System.setProperties(properties);
    }

    public void merge(Properties properties) {
        final Properties initialProperties = System.getProperties();
        Properties mergedProperties = new Properties();
        mergedProperties.putAll(initialProperties);
        mergedProperties.putAll(properties);
        System.setProperties(mergedProperties);
    }
}
