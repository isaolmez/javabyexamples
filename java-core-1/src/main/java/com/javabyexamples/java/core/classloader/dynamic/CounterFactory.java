package com.javabyexamples.java.core.classloader.dynamic;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CounterFactory {

    public static Counter newInstance() throws Exception {
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{getClassPath()}) {
            @Override
            public Class loadClass(String name) throws ClassNotFoundException {
                if ("com.javabyexamples.java.core.classloader.dynamic.DefaultCounter".equals(name)) {
                    return findClass(name);
                }

                return super.loadClass(name);
            }
        };

        return (Counter) urlClassLoader
          .loadClass("com.javabyexamples.java.core.classloader.dynamic.DefaultCounter")
          .newInstance();
    }

    private static URL getClassPath() {
        try {
            return new URL("file:/home/isa/git/github/javabyexamples/java-core-1/target/classes/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
