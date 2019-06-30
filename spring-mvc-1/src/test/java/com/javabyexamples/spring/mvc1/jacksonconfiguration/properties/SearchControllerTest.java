package com.javabyexamples.spring.mvc1.jacksonconfiguration.properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabyexamples.spring.mvc1.jacksonconfiguration.Query;
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
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("jacksonconfigure")
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldProcessJson() throws Exception {
        Query query = new Query();
        query.setSearchTerm("spring");
        mockMvc.perform(get("/search")
          .content(objectMapper.writeValueAsString(query))
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.search_term").exists())
          .andExpect(jsonPath("$.date").exists())
          .andExpect(jsonPath("$.total_count").exists());
    }

    @Test
    public void shouldNotFailOnEmptyBeans_AndReturnNonNull() throws Exception {
        mockMvc.perform(get("/search")
          .content("{}")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.search_term").doesNotExist())
          .andExpect(jsonPath("$.date").exists())
          .andExpect(jsonPath("$.total_count").exists());
    }
}