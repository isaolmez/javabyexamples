package com.javabyexamples.spring.core.usingbean.beannaming;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testBeanRegistration_WithMethodName() {
        assertThat(context.containsBean("greetingService")).isTrue();
    }

    @Test
    public void testBeanRegistration_WithBeanAnnotation() {
        assertThat(context.containsBean("firstGreetingService")).isTrue();
        assertThat(context.containsBean("theGreetingService")).isTrue();
    }

    @Test
    public void testBeanRegistration_WithQualifierAnnotation() {
        assertThat(context.containsBean("serviceWithQualifier")).isTrue();
        assertThat(context.containsBean("qualifiedGreetingService")).isFalse();
    }
}
