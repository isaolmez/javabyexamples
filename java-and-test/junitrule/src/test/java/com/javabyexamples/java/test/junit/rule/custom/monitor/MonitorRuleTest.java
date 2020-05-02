package com.javabyexamples.java.test.junit.rule.custom.monitor;

import org.junit.Rule;
import org.junit.Test;

public class MonitorRuleTest {

    @Rule
    public MonitorRule monitorRule = new MonitorRule();

    @Test
    public void shouldMonitor() {
        System.out.println("Actual test code...");
    }
}
