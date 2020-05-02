package com.javabyexamples.java.test.junit.rule.builtin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ErrorCollectorRuleTest {

    @Rule
    public final ErrorCollector collector = new ErrorCollector();

    @Test
    public void shouldCollectErrors() {
        Service service = new Service();
        if (!service.canRead()) {
            collector.addError(new Throwable("Cannot read!"));
        }

        if (!service.canWrite()) {
            collector.addError(new Throwable("Cannot write!"));
        }
    }

    public static class Service {

        public boolean canRead() {
            return false;
        }

        public boolean canWrite() {
            return false;
        }
    }
}
