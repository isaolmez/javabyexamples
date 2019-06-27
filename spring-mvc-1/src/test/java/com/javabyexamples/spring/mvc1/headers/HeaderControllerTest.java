package com.javabyexamples.spring.mvc1.headers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class HeaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetHeader() throws Exception {
        mockMvc.perform(get("/getHeader")
          .accept(MediaType.TEXT_PLAIN))
          .andExpect(status().isOk())
          .andExpect(content().string("text/plain"));
    }

    @Test
    public void shouldGetHeaderFromRequest() throws Exception {
        mockMvc.perform(get("/getHeaderFromRequest")
          .accept(MediaType.TEXT_PLAIN))
          .andExpect(status().isOk())
          .andExpect(content().string("text/plain"));
    }

    @Test
    public void shouldGetHeaderFromEntity() throws Exception {
        mockMvc.perform(get("/getHeaderFromEntity")
          .accept(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("application/json")));
    }

    @Test
    public void shouldGetAllHeaders() throws Exception {
        mockMvc.perform(get("/getHeaders")
          .header("header1", "value1")
          .header("header2", "value2"))
          .andExpect(status().isOk())
          .andExpect(header().string("header1", "value1"))
          .andExpect(header().string("header2", "value2"));
    }

    @Test
    public void shouldGetAllHeadersFromMap() throws Exception {
        mockMvc.perform(get("/getHeadersFromMap").accept(MediaType.APPLICATION_JSON_UTF8)
          .header("header1", "value1")
          .header("header2", "value2"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.header1").value("value1"))
          .andExpect(jsonPath("$.header2").value("value2"));
    }

    @Test
    public void shouldGetAllHeadersFromMultiValue() throws Exception {
        mockMvc.perform(get("/getHeadersFromMultiValue").accept(MediaType.APPLICATION_JSON_UTF8)
          .header("header1", "value1")
          .header("header2", "value2"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.header1").value("value1"))
          .andExpect(jsonPath("$.header2").value("value2"));
    }

    @Test
    public void shouldSetHeader() throws Exception {
        mockMvc.perform(get("/setHeader").accept(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(status().isOk())
          .andExpect(header().string("X-Source", "Spring"));
    }

    @Test
    public void shouldSetHeaderWithHttpHeaders() throws Exception {
        mockMvc.perform(get("/setHeaderWithHttpHeaders").accept(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(status().isOk())
          .andExpect(header().string("X-Source", "Spring"));
    }

    @Test
    public void shouldSetHeaderWithHttpEntity() throws Exception {
        mockMvc.perform(get("/setHeaderWithHttpEntity").accept(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(status().isOk())
          .andExpect(header().string("X-Source", "Spring"));
    }
}