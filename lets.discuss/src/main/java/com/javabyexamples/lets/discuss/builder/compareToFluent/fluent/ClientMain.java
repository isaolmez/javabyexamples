package com.javabyexamples.lets.discuss.builder.compareToFluent.fluent;


public class ClientMain {

    public static void main(String[] args) {
        new Employee().firstName("John")
                      .lastName("Doe")
                      .department("Accounting")
                      .startTime(10)
                      .endTime(18);
    }
}
