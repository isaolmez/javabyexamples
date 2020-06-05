package com.javabyexamples.maven.plugins.buildhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AddResources {

    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("imported.properties");

        if (inputStream != null) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            System.out.println(properties);
        }
    }
}
