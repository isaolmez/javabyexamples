package com.javabyexamples.maven.plugins.resources;

import com.javabyexamples.maven.plugins.resources.helper.IOHelper;
import java.io.IOException;
import java.io.InputStream;

public class CopyResources {

    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("additional-resources/extraResource.txt");
        IOHelper.print(inputStream);
    }
}
