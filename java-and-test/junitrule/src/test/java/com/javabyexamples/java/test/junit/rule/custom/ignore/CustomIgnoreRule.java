package com.javabyexamples.java.test.junit.rule.custom.ignore;

import org.junit.Assume;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomIgnoreRule implements TestRule {

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
            CustomIgnore annotation = description.getAnnotation(CustomIgnore.class);
            boolean shouldIgnore = annotation != null;
            Assume.assumeTrue("Test is ignored!", !shouldIgnore);
            base.evaluate();
        }
    }
}
