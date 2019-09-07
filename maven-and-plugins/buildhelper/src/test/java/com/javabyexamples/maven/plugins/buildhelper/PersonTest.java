package com.javabyexamples.maven.plugins.buildhelper;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    private Person person = new Person();

    @Test
    public void shouldWalk() {
        String result = person.walk();

        Assert.assertEquals("walking", result);
    }
}