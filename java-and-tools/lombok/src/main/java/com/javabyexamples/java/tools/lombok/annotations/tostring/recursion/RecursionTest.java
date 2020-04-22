package com.javabyexamples.java.tools.lombok.annotations.tostring.recursion;

public class RecursionTest {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Developer developer = new Developer();
        manager.setSubordinate(developer);
        developer.setManager(manager);
        System.out.println(developer.toString());
    }
}
