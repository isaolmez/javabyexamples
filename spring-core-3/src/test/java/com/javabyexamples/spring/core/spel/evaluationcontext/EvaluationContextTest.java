package com.javabyexamples.spring.core.spel.evaluationcontext;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.spring.core.spel.rootobject.Address;
import com.javabyexamples.spring.core.spel.rootobject.Person;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public class EvaluationContextTest {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void shouldSetNestedProperty() {
        Person person = new Person();
        person.setAddress(new Address());
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();

        parser.parseExpression("address.city").setValue(context, person, "Sydney");

        assertThat(person.getAddress().getCity()).isEqualTo("Sydney");
    }

    @Test
    public void shouldConvert() {
        Person person = new Person();
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();

        parser.parseExpression("married").setValue(context, person, "true");

        assertThat(person.isMarried()).isTrue();
    }

    @Test
    public void shouldInitialize() {
        final Expression expression = parser.parseExpression("address");
        final Person person = new Person();
        person.setName("John");
        final Address address = expression.getValue(person, Address.class);

        assertThat(address).isNotNull();
    }
}
