package com.javabyexamples.lets.discuss.builder.compareToFluent.builder;

import com.javabyexamples.lets.discuss.builder.compareToFluent.builder.Employee.Builder;

public class ClientMain {

    public static void main(String[] args) {
        new Builder().name("John")
                     .lastName("Doe")
                     .department("Accounting")
                     .startTime(10)
                     .endTime(18)
                     .build();
    }
}
