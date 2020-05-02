package com.javabyexamples.java.test.junit.rule.custom.conditionalignore;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assume;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ConditionalIgnoreRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new IgnorableStatement(base, description);
    }

    private class IgnorableStatement extends Statement {

        private final Statement base;

        private final Description description;

        public IgnorableStatement(Statement base, Description description) {
            this.base = base;
            this.description = description;
        }

        @Override
        public void evaluate() throws Throwable {
            boolean shouldIgnore = false;
            ConditionalIgnore annotation = description.getAnnotation(ConditionalIgnore.class);
            if (annotation != null) {
                String key = annotation.key();
                String value = annotation.value();
                String actualValue = System.getProperty(key);
                if (!StringUtils.equalsIgnoreCase(value, actualValue)) {
                    shouldIgnore = true;
                }
            }

            Assume.assumeTrue("Test is ignored!", !shouldIgnore);
            base.evaluate();
        }
    }
}
