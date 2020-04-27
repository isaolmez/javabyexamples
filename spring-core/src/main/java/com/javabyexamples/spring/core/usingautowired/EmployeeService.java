package com.javabyexamples.spring.core.usingautowired;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    public void work() {
        System.out.println("Working");
    }
}
