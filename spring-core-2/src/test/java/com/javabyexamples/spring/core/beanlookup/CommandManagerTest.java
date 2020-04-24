package com.javabyexamples.spring.core.beanlookup;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class CommandManagerTest {

    @Autowired
    private CommandManager commandManager;

    @Test
    public void testProcess() {
        final String result1 = commandManager.process();
        final String result2 = commandManager.process();

        assertThat(result1).isNotEqualTo(result2);
    }

    @Test
    public void testProcessWithQualifier() {
        final String result1 = commandManager.processWithQualifier();
        final String result2 = commandManager.processWithQualifier();

        assertThat(result1).isNotEqualTo(result2);
    }
}
