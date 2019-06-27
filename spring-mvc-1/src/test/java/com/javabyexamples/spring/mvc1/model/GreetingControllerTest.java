package com.javabyexamples.spring.mvc1.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPopulateModel() throws Exception {
        mockMvc.perform(get("/get").param("name", "Java")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello <span>Java</span>!")));
    }

    @Test
    public void shouldPopulateModelWithModelMap() throws Exception {
        mockMvc.perform(get("/getWithModelMap").param("name", "Java")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello <span>Java</span>!")));
    }

    @Test
    public void shouldPopulateModelWithModel() throws Exception {
        mockMvc.perform(get("/getWithModel").param("name", "Java")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello <span>Java</span>!")));
    }

    @Test
    public void shouldPopulateModelWithMap() throws Exception {
        mockMvc.perform(get("/getWithMap").param("name", "Java")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello <span>Java</span>!")));
    }
}