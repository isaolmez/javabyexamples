package com.javabyexamples.java.test.junit.rule.custom.conditionalignore;

import org.junit.Rule;
import org.junit.Test;

public class ConditionalIgnoreTest {

    @Rule
    public ConditionalIgnoreRule conditionalIgnoreRule = new ConditionalIgnoreRule();

    @ConditionalIgnore(key = "line.separator", value = "\n")
    @Test
    public void shouldRunIfType1() {
        System.out.println("Type 1");
    }

    @ConditionalIgnore(key = "type", value = "type2")
    @Test
    public void shouldRunIfType2() {
        System.out.println("Type 2");
    }
}
