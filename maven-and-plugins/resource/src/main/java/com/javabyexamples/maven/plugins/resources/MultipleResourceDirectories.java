package com.javabyexamples.maven.plugins.resources;

import com.javabyexamples.maven.plugins.resources.helper.IOHelper;
import java.io.IOException;
import java.io.InputStream;

public class MultipleResourceDirectories {

    public static void main(String[] args) throws IOException {
        final InputStream intResource = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("intResource.txt");
        if (intResource != null) {
            IOHelper.print(intResource);
        }

        final InputStream prodResource = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("prodResource.txt");
        if (prodResource != null) {
            IOHelper.print(prodResource);
        }
    }
}
