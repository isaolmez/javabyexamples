package com.javabyexamples.maven.plugins.compiler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

    @Test
    public void shouldPrintVersion() {
        List<String> names = Collections.singletonList("john");

        List<String> upperNames = names.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());

        Assert.assertTrue(upperNames.contains("JOHN"));
    }
}

