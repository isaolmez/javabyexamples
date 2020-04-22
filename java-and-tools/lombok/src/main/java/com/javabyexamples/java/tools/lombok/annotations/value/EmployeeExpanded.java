package com.javabyexamples.java.tools.lombok.annotations.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeExpanded {

    private String name;
    private int salary;
}
