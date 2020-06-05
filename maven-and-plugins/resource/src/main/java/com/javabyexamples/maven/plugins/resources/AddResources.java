package com.javabyexamples.maven.plugins.resources;

import com.javabyexamples.maven.plugins.resources.helper.IOHelper;
import java.io.IOException;
import java.io.InputStream;

public class AddResources {

    public static void main(String[] args) throws IOException {
        final InputStream intResource = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("int/intResource.txt");
        if (intResource != null) {
            IOHelper.print(intResource);
        }

        final InputStream prodResource = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("prod/prodResource.txt");
        if (prodResource != null) {
            IOHelper.print(prodResource);
        }
    }
}
