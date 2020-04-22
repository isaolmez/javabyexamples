package com.javabyexamples.java.tools.lombok.annotations.nonnull;

public class EmployeeServiceDelomboked {

    public void increaseSalary(String name) {
        if (name == null) {
            throw new NullPointerException("name is marked @NonNull but is null");
        }

        System.out.println(name);
    }
}
