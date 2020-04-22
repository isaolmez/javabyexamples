package com.javabyexamples.java.core.formatter;

public class GeneralFormatterTest {

    public static void main(String[] args) {
        GeneralFormatterTest formatter = new GeneralFormatterTest();
        formatter.basicUsage();
        formatter.order();
    }

    public void basicUsage() {
        String greet = String.format("Hello %s", "John");
        System.out.println(greet);
    }

    public void order() {
        String greet = String.format("Hello %2$s and %1$s", "John", "Jane");
        System.out.println(greet);
    }
}
