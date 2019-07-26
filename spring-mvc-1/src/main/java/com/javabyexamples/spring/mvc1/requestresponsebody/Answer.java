package com.javabyexamples.spring.mvc1.requestresponsebody;

import lombok.Data;

@Data
public class Answer {

    private String answerMessage;
    private Question question;
}
