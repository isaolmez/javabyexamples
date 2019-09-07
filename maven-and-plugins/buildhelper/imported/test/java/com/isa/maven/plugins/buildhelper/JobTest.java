package com.javabyexamples.maven.plugins.buildhelper;

import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    private Job job = new Job();

    @Test
    public void shouldWork(){
        String result = job.work();

        Assert.assertEquals("working", result);
    }
}