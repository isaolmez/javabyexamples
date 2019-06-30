package com.javabyexamples.spring.mvc1.requestresponsebody;

import lombok.Data;

@Data
public class Answer {

    private Question question;
    private String answerMessage;
}
