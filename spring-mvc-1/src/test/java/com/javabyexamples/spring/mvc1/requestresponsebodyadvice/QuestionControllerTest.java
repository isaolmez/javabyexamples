package com.javabyexamples.spring.mvc1.requestresponsebodyadvice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("default")
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldApplyAdvices() throws Exception {
        Question question = new Question();
        question.setQuestionMessage("How is weather?");
        mockMvc.perform(post("/ask")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(objectMapper.writeValueAsString(question))
          .accept(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    }
}