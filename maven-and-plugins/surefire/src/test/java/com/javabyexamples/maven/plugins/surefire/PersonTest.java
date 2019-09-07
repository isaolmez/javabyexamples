package com.javabyexamples.maven.plugins.surefire;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    private Person person = new Person();

    @Test
    public void shouldSayHello() {
        String result = person.sayHello();

        Assert.assertEquals("Hello", result);
    }
}