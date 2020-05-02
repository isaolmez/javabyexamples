package com.javabyexamples.java.test.junit.rule.builtin;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TestNameRuleTest {

    @Rule
    public TestName testName = new TestName();

    @Test
    public void shouldGetTestName() {
        assertThat(testName.getMethodName()).isEqualTo("shouldGetTestName");
    }
}
