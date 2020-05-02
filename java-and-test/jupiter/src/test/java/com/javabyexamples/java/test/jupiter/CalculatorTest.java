package com.javabyexamples.java.test.jupiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    @DisplayName("Should multiply two given integers")
    public void shouldMultiply() {
        int result = calculator.multiply(2, 3);

        Assertions.assertEquals(6, result);
    }
}
