package com.javabyexamples.maven.plugins.surefire.groups;

import com.javabyexamples.maven.plugins.surefire.Person;
import org.junit.Assert;
import org.junit.Test;

public class RegularPersonTest {

    private Person person = new Person();

    @Test
    public void run() {
        String result = person.run();

        Assert.assertEquals("Running", result);
    }
}