package com.javabyexamples.spring.mvc1.requestresponsebody;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuestionController {

    @GetMapping(value = "/question", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer question(@RequestBody Question question) {
        Answer answer = new Answer();
        answer.setAnswerMessage("Work hard!");
        return answer;
    }
}
