package com.javabyexamples.maven.plugins.surefire;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SampleTest {

    private Person person = new Person();

    @Test
    public void shouldRun() {
        assertEquals("Running", person.run());
    }
}
