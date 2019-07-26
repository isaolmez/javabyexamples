package com.javabyexamples.spring.mvc1.requestresponsebody;

import lombok.Data;

@Data
public class PersonWithoutXMLAnnotation {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
}
