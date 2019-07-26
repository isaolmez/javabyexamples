package com.javabyexamples.spring.mvc1.templatelocation;

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
public class TemplateLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetTemplateFromDefault() throws Exception {
        mockMvc.perform(get("/welcome")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome"));
    }

    @Test
    public void shouldGetTemplate() throws Exception {
        mockMvc.perform(get("/hi")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi"));
    }

    @Test
    public void shouldGetTemplateFromInner() throws Exception {
        mockMvc.perform(get("/bye")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("Bye"));
    }

    @Test
    public void shouldGetTemplateFromSpringResource() throws Exception {
        mockMvc.perform(get("/farewell")
          .accept(MediaType.TEXT_HTML))
          .andExpect(status().isOk())
          .andExpect(content().string("Farewell"));
    }
}