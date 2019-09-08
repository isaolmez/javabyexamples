package com.javabyexamples.maven.plugins.property;

import org.junit.Assert;
import org.junit.Test;

public class SetPropertyTest {

    @Test
    public void shouldRun() {
        Assert.assertEquals("myValue", System.getProperty("myCustomProperty"));
    }
}