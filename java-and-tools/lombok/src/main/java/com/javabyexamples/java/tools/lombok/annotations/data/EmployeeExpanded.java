package com.javabyexamples.java.tools.lombok.annotations.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeExpanded {

    private String name;
    private int salary;
}
