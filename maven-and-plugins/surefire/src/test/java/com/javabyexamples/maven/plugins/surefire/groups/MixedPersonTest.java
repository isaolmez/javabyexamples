package com.javabyexamples.maven.plugins.surefire.groups;

import com.javabyexamples.maven.plugins.surefire.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MixedPersonTest {

    private Person person = new Person();

    @Category(FastTest.class)
    @Test
    public void runsFast() {
        String result = person.run();

        Assert.assertEquals("Running", result);
    }

    @Category(SlowTest.class)
    @Test
    public void runsSlow() {
        String result = person.runTenLaps();

        Assert.assertEquals("Running ten laps", result);
    }
}