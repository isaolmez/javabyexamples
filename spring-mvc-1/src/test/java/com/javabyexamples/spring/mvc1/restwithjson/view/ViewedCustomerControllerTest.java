package com.javabyexamples.spring.mvc1.restwithjson.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabyexamples.spring.mvc1.restwithjson.Query;
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
public class ViewedCustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnPublicDetails() throws Exception {
        Query query = new Query();
        query.setName("john");
        mockMvc.perform(get("/view/public")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(query))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.country").exists())
                .andExpect(jsonPath("$.creditCardNumber").exists());
    }

    @Test
    public void shouldReturnPrivateDetails() throws Exception {
        Query query = new Query();
        query.setName("john");
        mockMvc.perform(get("/view/private")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(query))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.lastName").doesNotExist())
                .andExpect(jsonPath("$.country").exists())
                .andExpect(jsonPath("$.creditCardNumber").exists());
    }
}