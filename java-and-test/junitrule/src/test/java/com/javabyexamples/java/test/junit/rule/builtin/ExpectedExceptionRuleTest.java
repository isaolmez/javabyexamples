package com.javabyexamples.java.test.junit.rule.builtin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionRuleTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Value is null");

        throw new NullPointerException("Value is null");
    }
}
