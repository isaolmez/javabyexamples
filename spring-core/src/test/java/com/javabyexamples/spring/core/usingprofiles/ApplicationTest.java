package com.javabyexamples.spring.core.usingprofiles;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles({"fast", "prod"})
public class ApplicationTest {

    @Autowired
    private Environment environment;

    @Test
    public void shouldActivateProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();

        assertThat(activeProfiles).containsOnly("fast", "prod");
    }
}
