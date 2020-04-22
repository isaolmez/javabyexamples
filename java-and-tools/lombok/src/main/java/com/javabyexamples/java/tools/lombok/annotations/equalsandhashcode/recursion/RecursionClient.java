package com.javabyexamples.java.tools.lombok.annotations.equalsandhashcode.recursion;

public class RecursionClient {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Employee employee = new Employee();
        manager.setSubordinate(employee);
        employee.setManager(manager);
        System.out.println(employee.hashCode());
    }
}
