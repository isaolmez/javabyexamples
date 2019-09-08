package com.javabyexamples.maven.plugins.failsafe.systemproperty;

import org.junit.Assert;
import org.junit.Test;

public class SystemPropertyIT {

    @Test
    public void shouldAccessSystemProperty_WhenDefinedInSystemPropertyVariables() {
        Assert.assertEquals("Hello", System.getProperty("greet.english"));
    }

    @Test
    public void shouldAccessSystemProperty_WhenDefinedInSystemProperties() {
        Assert.assertEquals("Hola", System.getProperty("greet.spanish"));
    }

    @Test
    public void shouldAccessSystemProperty_WhenDefinedInSystemPropertiesFile() {
        Assert.assertEquals("Hallo", System.getProperty("greet.german"));
    }
}
