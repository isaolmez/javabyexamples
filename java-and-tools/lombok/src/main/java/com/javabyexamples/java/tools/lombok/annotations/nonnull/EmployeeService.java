package com.javabyexamples.java.tools.lombok.annotations.nonnull;

import lombok.NonNull;

public class EmployeeService {

    public void increaseSalary(@NonNull String name) {
        System.out.println(name);
    }
}
