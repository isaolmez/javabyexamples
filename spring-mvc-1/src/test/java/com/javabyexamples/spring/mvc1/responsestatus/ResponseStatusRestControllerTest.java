package com.javabyexamples.spring.mvc1.responsestatus;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class ResponseStatusRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSetStatus() throws Exception {
        mockMvc.perform(get("/status"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldSetStatusForPost() throws Exception {
        mockMvc.perform(post("/statusPost"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldSetStatusForPut() throws Exception {
        mockMvc.perform(put("/statusPut"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldSetStatusForDelete() throws Exception {
        mockMvc.perform(delete("/statusDelete"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldSetStatusForPatch() throws Exception {
        mockMvc.perform(patch("/statusPatch"))
          .andExpect(status().isOk());
    }

    @Test
    public void shouldSetStatusWithResponseEntity() throws Exception {
        mockMvc.perform(post("/statusWithResponseEntity"))
          .andExpect(status().isAccepted());
    }

    @Test
    public void shouldSetStatusWithServletResponse() throws Exception {
        mockMvc.perform(post("/statusWithResponse"))
          .andExpect(status().isAccepted());
    }

    @Test
    public void shouldSetStatusWithAnnotation() throws Exception {
        mockMvc.perform(post("/statusWithAnnotation"))
          .andExpect(status().isAccepted());
    }
}