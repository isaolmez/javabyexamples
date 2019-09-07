package com.javabyexamples.maven.plugins.failsafe.groups;

import com.javabyexamples.maven.plugins.failsafe.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTest.class)
public class Person1IT {

    private Person person = new Person();

    @Test
    public void shouldSayHello() {
        String result = person.sayHello();

        Assert.assertEquals("Hello", result);
    }
}