package com.javabyexamples.spring.mvc1.serverconfiguration.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("serverproperties")
public class PingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldPopulateModel() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/ping", String.class);

        assertThat(responseEntity.getHeaders().get("server")).containsOnly("MyAppServer");
    }
}