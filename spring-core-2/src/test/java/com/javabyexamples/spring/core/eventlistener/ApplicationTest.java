package com.javabyexamples.spring.core.eventlistener;

import com.javabyexamples.spring.core.componentscan.aspectj.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }
}
