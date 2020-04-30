package com.javabyexamples.spring.core.spel.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELTest {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void shouldHandleStringLiteral() {
        Expression exp = parser.parseExpression("'Hello World!'");
        String message = (String) exp.getValue();

        assertThat(message).isEqualTo("Hello World!");
    }

    @Test
    public void shouldHandleMethodCall() {
        Expression exp = parser.parseExpression("'Hello World'.concat('!').toLowerCase()");
        String message = exp.getValue(String.class);

        assertThat(message).isEqualTo("hello world!");
    }

    @Test
    public void shouldAccessJavaBeanProperty() {
        Expression exp = parser.parseExpression("'Hello'.bytes");
        byte[] bytes = (byte[]) exp.getValue();

        assertThat(bytes).isEqualTo("Hello".getBytes());
    }

    @Test
    public void shouldAccessJavaBeanNestedProperty() {
        Expression exp = parser.parseExpression("new com.javabyexamples.spring.core.spel.basic.Person().address.city");
        String city = (String) exp.getValue();

        assertThat(city).isEqualTo("unknown");
    }
}
