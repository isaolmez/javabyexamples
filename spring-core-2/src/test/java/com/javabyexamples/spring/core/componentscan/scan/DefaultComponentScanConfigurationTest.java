package com.javabyexamples.spring.core.componentscan.scan;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.spring.core.componentscan.level1.Level1Service;
import com.javabyexamples.spring.core.componentscan.level1.level2.Level2Repository;
import com.javabyexamples.spring.core.componentscan.level1.level2.Level2Service;
import com.javabyexamples.spring.core.componentscan.level1.level2.MockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DefaultComponentScanConfiguration.class)
public class DefaultComponentScanConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testThatBeansAreScanned() {
        assertThat(context.getBean(Level1Service.class)).isNotNull();
        assertThat(context.getBean(Level2Service.class)).isNotNull();
        assertThat(context.getBean(Level2Repository.class)).isNotNull();

        assertThat(context.getBeansOfType(MockRepository.class)).isEmpty();
    }
}
