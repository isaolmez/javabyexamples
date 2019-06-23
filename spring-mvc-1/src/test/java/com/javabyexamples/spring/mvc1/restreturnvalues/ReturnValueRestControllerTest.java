package com.javabyexamples.spring.mvc1.restreturnvalues;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
@AutoConfigureMockMvc
public class ReturnValueRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnValue() throws Exception {
        mockMvc.perform(get("/get")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void shouldReturnNoValue() throws Exception {
        mockMvc.perform(get("/getNoBody")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldReturnValueWithHttpEntity() throws Exception {
        mockMvc.perform(get("/getWithHttpEntity")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void shouldReturnValueWithResponseEntity() throws Exception {
        mockMvc.perform(get("/getWithResponseEntity")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void shouldReturnNoValueButHeaders() throws Exception {
        mockMvc.perform(get("/getHeaders")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andExpect(header().exists("Additional"));
    }
}