package com.javabyexamples.spring.mvc1.restwithxml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("details")
public class PersonDetails {

    private String name;

    private String lastName;

    private int age;
}
