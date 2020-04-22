package com.javabyexamples.java.core.systemproperty;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;
import org.junit.Test;

public class SystemPropertiesOperationsTest {

    private SystemPropertiesOperations operations = new SystemPropertiesOperations();
    private final String key = "hello";
    private final String value = "world";

    @Test
    public void testGet() {
        final String os = operations.get("java.version");

        assertThat(os).isNotEmpty();
    }

    @Test
    public void testSet() {
        operations.set(key, value);

        final String actualValue = operations.get(key);

        assertThat(actualValue).isEqualTo(value);
    }

    @Test
    public void testRemove() {
        operations.set(key, value);
        operations.remove(key);

        final String actualValue = operations.get(key);

        assertThat(actualValue).isNull();
    }

    @Test
    public void testGetAll() {
        final Properties properties = operations.getAll();

        assertThat(properties).isNotEmpty();
    }

    @Test
    public void testSetAll() {
        final Properties properties = new Properties();
        properties.setProperty(key, value);

        operations.setAll(properties);

        final String actualValue = operations.get(key);
        assertThat(actualValue).isEqualTo(value);

        final String os = operations.get("java.version");
        assertThat(os).isNull();
    }

    @Test
    public void testMerge() {
        final Properties properties = new Properties();
        properties.setProperty(key, value);

        operations.merge(properties);

        final String actualValue = operations.get(key);
        assertThat(actualValue).isEqualTo(value);

        final String os = operations.get("java.version");
        assertThat(os).isNotNull();
    }
}
