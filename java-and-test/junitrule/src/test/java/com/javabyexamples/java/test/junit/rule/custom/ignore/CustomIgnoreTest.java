package com.javabyexamples.java.test.junit.rule.custom.ignore;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Rule;
import org.junit.Test;

public class CustomIgnoreTest {

    @Rule
    public CustomIgnoreRule customIgnoreRule = new CustomIgnoreRule();

    @CustomIgnore
    @Test
    public void shouldIgnore() {
        fail("Should have been ignored!");
    }

    @Test
    public void shouldNotIgnore() {
        System.out.println("Hello World!");
    }
}
