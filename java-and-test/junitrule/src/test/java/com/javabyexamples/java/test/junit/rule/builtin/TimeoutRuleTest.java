package com.javabyexamples.java.test.junit.rule.builtin;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TimeoutRuleTest {

    @Rule
    public Timeout timeout = new Timeout(1000, TimeUnit.MILLISECONDS);

    @Test
    public void shouldNotTimeout() {
        sleep(500);
    }

    @Test
    public void shouldTimeout() {
        sleep(1500);
    }

    @Test(timeout = 3000)
    public void shouldTimeout_WithSmallest() {
        sleep(1500);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            fail();
        }
    }
}
