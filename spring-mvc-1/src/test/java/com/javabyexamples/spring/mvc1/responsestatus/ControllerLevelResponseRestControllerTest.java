package com.javabyexamples.spring.mvc1.responsestatus;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class ControllerLevelResponseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSetStatusOnController() throws Exception {
        mockMvc.perform(post("/controller/status"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldOverrideStatusOnMethod() throws Exception {
        mockMvc.perform(post("/controller/statusOverride"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldSetStatusFromExceptionHandler() throws Exception {
        mockMvc.perform(post("/controller/statusWithThrowing"))
                .andExpect(status().isInternalServerError());
    }
}