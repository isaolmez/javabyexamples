package com.javabyexamples.maven.plugins.surefire.groups;

import com.javabyexamples.maven.plugins.surefire.Person;
import org.junit.Assert;
import org.junit.Test;

public class RegularPersonTest {

    private Person person = new Person();

    @Test
    public void shouldSayHello() {
        String result = person.sayHello();

        Assert.assertEquals("Hello", result);
    }
}