package com.javabyexamples.spring.mvc1.requestresponsebody;

import java.util.Date;
import lombok.Data;

@Data
public class Question {

    private String questionMessage;
    private Date date;
}
