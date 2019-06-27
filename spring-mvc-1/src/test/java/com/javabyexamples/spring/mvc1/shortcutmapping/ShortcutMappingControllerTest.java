package com.javabyexamples.spring.mvc1.shortcutmapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class ShortcutMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPerformGet() throws Exception {
        mockMvc.perform(get("/greetGet").param("name", "Spring"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPerformPost() throws Exception {
        mockMvc.perform(post("/greetPost").param("name", "Spring"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPerformDelete() throws Exception {
        mockMvc.perform(delete("/greetDelete").param("name", "Spring"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPerformPut() throws Exception {
        mockMvc.perform(put("/greetPut").param("name", "Spring"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPerformPatch() throws Exception {
        mockMvc.perform(patch("/greetPatch").param("name", "Spring"))
                .andExpect(status().isOk());
    }
}