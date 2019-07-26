package com.javabyexamples.spring.mvc1.requestresponsebodyadvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuestionController {

    @PostMapping(value = "/ask", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer ask(@RequestBody Question question) {
        System.out.println("In controller method");
        Answer answer = new Answer();
        answer.setAnswerMessage("I don't know!");
        answer.setQuestion(question);
        return answer;
    }
}
