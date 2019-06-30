package com.javabyexamples.spring.mvc1.requestmapping.paths;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private String name;
    private String department;
    private Date date;
}
