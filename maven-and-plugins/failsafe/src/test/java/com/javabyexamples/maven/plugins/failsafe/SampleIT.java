package com.javabyexamples.maven.plugins.failsafe;

import org.junit.Assert;
import org.junit.Test;

public class SampleIT {

    private Person person = new Person();

    @Test
    public void shouldSayHello() {
        Assert.assertEquals("Hello", person.sayHello());
    }

    @Test
    public void shouldSayBye() {
        Assert.assertEquals("Bye", person.sayBye());
    }
}
