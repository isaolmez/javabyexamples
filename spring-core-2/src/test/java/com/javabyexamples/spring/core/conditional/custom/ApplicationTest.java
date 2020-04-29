package com.javabyexamples.spring.core.conditional.custom;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testThatConditionMatches() {
        final Map<String, AuditService> beans = context.getBeansOfType(AuditService.class);

        assertThat(beans).hasSize(1);
    }
}
