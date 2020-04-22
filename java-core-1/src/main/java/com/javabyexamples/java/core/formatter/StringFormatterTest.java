package com.javabyexamples.java.core.formatter;

public class StringFormatterTest {

    public static void main(String[] args) {
        StringFormatterTest formatter = new StringFormatterTest();
        formatter.strings();
        formatter.characters();
    }

    public void strings() {
        System.out.println("###### Strings");
        System.out.format("' %s '%n", "Hello!");
        System.out.format("' %s '%n", 12);
        System.out.format("' %s '%n", 12.01);
        System.out.format("' %S '%n", "Hello!");

        System.out.format("' %1$s '%n", "Hello!");
        System.out.format("' %1$s %1$s '%n", "Hello!");

        System.out.format("' %.2s '%n", "Hello!");
        System.out.format("' %10.2s '%n", "Hello!");
        System.out.format("' %-10.2s '%n", "Hello!");
    }

    public void characters() {
        System.out.println("###### Characters");
        System.out.format("' %c '%n", 'k');
        System.out.format("' %C '%n", 'k');
    }
}
