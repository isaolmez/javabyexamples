package com.javabyexamples.spring.core.usingautowired;

import org.springframework.stereotype.Component;

@Component
public class ManagerService {

    public void manage() {
        System.out.println("Managing");
    }
}
