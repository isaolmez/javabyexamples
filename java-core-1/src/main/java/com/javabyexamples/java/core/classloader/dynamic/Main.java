package com.javabyexamples.java.core.classloader.dynamic;

import java.net.URLClassLoader;
import java.util.Arrays;

public class Main {

    private static Counter counter1;
    private static Counter counter2;

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Main.class.getClassLoader();
        URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
        System.out.println(urlClassLoader);
        System.out.println(Arrays.toString(urlClassLoader.getURLs()));
        counter1 = CounterFactory.newInstance();

        while (true) {
            counter2 = CounterFactory.newInstance();

            System.out.println("1) " + counter1.message() + " = " + counter1.plusPlus());
            System.out.println("2) " + counter2.message() + " = " + counter2.plusPlus());
            System.out.println();

            Thread.sleep(3000);
        }
    }
}
