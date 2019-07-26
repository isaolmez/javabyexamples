package com.javabyexamples.spring.mvc1.requestresponsebody;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
}
