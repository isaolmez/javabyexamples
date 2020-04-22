package com.javabyexamples.java.core.environmentvariable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Test;

public class EnvironmentVariableOperationsTest {

    private EnvironmentVariableOperations operations = new EnvironmentVariableOperations();

    private final String key = "hello";
    private final String value = "world";

    @Test
    public void testGet() {
        final String path = operations.get("PATH");

        assertThat(path).isNotEmpty();
    }

    @Test
    public void testGetAll() {
        final Map<String, String> variables = operations.getAll();

        assertThat(variables).isNotEmpty();
    }
}
