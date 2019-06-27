package com.javabyexamples.spring.mvc1.restwithxml.xstream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class MarshallingXstreamXmlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPerformGet() throws Exception {
        String personCriteriaXml = "<criteria><name>java</name><lastName>rocks</lastName></criteria>";
        mockMvc.perform(post("/marshallingXstream/person")
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .content(personCriteriaXml))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("<details><name>java</name><lastName>rocks</lastName><age>20</age></details>")));
    }
}