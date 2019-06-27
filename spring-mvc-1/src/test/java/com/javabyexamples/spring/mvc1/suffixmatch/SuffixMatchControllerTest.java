package com.javabyexamples.spring.mvc1.suffixmatch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class SuffixMatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOk_WhenThereIsNoExtension() throws Exception {
        mockMvc.perform(get("/hello"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldNotReturnOk_WhenThereIsExtension() throws Exception {
        mockMvc.perform(get("/hello.html"))
          .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOk_WhenCasingDoesNotMatch() throws Exception {
        mockMvc.perform(get("/HELLo"))
          .andExpect(status().isOk());
    }
}