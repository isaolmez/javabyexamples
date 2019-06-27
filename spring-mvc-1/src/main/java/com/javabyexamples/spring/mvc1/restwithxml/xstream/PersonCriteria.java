package com.javabyexamples.spring.mvc1.restwithxml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("criteria")
public class PersonCriteria {

    private String name;

    private String lastName;
}
