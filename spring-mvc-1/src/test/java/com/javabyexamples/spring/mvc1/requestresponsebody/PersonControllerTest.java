package com.javabyexamples.spring.mvc1.requestresponsebody;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreatePerson() throws Exception {
        mockMvc.perform(post("/person")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30}")
          .accept(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk())
          .andExpect(content().string(""));
    }

    @Test
    public void shouldGetPerson() throws Exception {
        mockMvc.perform(get("/person").param("id", "1")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .accept(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.firstName").value(containsString("John")))
          .andExpect(jsonPath("$.lastName").value(containsString("Doe")))
          .andExpect(jsonPath("$.age").value("30"));
    }

    @Test
    public void shouldCreatePerson_WhenMediaTypeIsXML() throws Exception {
        mockMvc.perform(post("/person")
          .contentType(MediaType.APPLICATION_XML_VALUE)
          .content("<person><age>30</age><firstName>John</firstName><lastName>Doe</lastName></person>")
          .accept(MediaType.APPLICATION_XML_VALUE))
          .andExpect(status().isOk())
          .andExpect(content().string(""));
    }

    @Test
    public void shouldGetPerson_WhenMediaTypeIsXML() throws Exception {
        mockMvc.perform(get("/person").param("id", "1")
          .contentType(MediaType.APPLICATION_XML_VALUE)
          .accept(MediaType.APPLICATION_XML_VALUE))
          .andExpect(status().isOk())
          .andExpect(xpath("/person/firstName").string(containsString("John")))
          .andExpect(xpath("/person/lastName").string(containsString("Doe")))
          .andExpect(xpath("/person/age").string(containsString("30")));
    }

    @Test
    public void shouldNotCreatePerson_WhenMediaTypeIsNotSupported() throws Exception {
        mockMvc.perform(post("/person")
          .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)
          .content("<person><age>30</age><firstName>John</firstName><lastName>Doe</lastName></person>")
          .accept(MediaType.APPLICATION_ATOM_XML_VALUE))
          .andExpect(status().is(415));
    }

    @Test
    public void shouldNotGetPerson_WhenMediaTypeIsNotSupported() throws Exception {
        mockMvc.perform(get("/person").param("id", "1")
          .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)
          .accept(MediaType.APPLICATION_ATOM_XML_VALUE))
          .andExpect(status().is(406));
    }
}