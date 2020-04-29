package com.javabyexamples.spring.core.conditional.condition;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(properties = "audit.enabled=false")
public class ApplicationWhenDisabledTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testThatConditionNotMatches() {
        final Map<String, AuditService> beans = context.getBeansOfType(AuditService.class);

        assertThat(beans).isEmpty();
    }
}
