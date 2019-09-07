package com.javabyexamples.maven.plugins.surefire.systemproperty;

import org.junit.Assert;
import org.junit.Test;

public class SystemPropertyTest {

    @Test
    public void shouldAccessSystemProperty(){
        Assert.assertEquals("Hello", System.getProperty("greet.english"));
        Assert.assertEquals("Hola", System.getProperty("greet.spanish"));
        Assert.assertEquals("Hallo", System.getProperty("greet.german"));
    }
}
