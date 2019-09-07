package com.javabyexamples.maven.plugins.surefire.groups;

import com.javabyexamples.maven.plugins.surefire.Person;
import org.junit.Assert;
import org.junit.Test;

public class Person1Test {

    private Person person = new Person();

    @Test
    public void shouldSayHello() {
        String result = person.sayHello();

        Assert.assertEquals("Hello", result);
    }
}