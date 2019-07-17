package com.javabyexamples.spring.mvc1.currentrequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldStore() throws Exception {
        mockMvc.perform(post("/person").content("{\"name\":\"John\"}").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isInternalServerError());
    }
}