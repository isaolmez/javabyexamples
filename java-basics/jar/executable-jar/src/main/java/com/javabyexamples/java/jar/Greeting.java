package com.javabyexamples.java.jar;

public class Greeting {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalStateException("Please enter your name and city.");
        }

        final String name = args[0];
        final String city = args[1];
        System.out.printf("Hello %s from %s%n", name, city);
    }
}
