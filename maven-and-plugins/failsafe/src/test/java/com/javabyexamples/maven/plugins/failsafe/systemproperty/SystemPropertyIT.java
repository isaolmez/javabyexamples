package com.javabyexamples.maven.plugins.failsafe.systemproperty;

import org.junit.Assert;
import org.junit.Test;

public class SystemPropertyIT {

    @Test
    public void shouldAccessSystemProperty() {
        Assert.assertEquals("Hello", System.getProperty("greet.english"));
        Assert.assertEquals("Hola", System.getProperty("greet.spanish"));
        Assert.assertEquals("Hallo", System.getProperty("greet.german"));
    }
}
