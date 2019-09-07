package com.javabyexamples.maven.plugins.property;

import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

    @Test
    public void shouldRun() {
        Assert.assertEquals("myValue", System.getProperty("my.custom.property"));
    }
}