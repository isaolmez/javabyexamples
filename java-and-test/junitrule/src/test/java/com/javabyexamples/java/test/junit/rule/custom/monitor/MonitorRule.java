package com.javabyexamples.java.test.junit.rule.custom.monitor;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class MonitorRule extends TestWatcher {

    /**
     * Invoked when a test succeeds
     */
    @Override
    protected void succeeded(Description description) {
        System.out.printf("%s succeeded%n", description.getMethodName());
    }

    /**
     * Invoked when a test fails
     */
    @Override
    protected void failed(Throwable e, Description description) {
        System.out.printf("%s failed with %s%n", description.getMethodName(), e);
    }

    /**
     * Invoked when a test is skipped due to a failed assumption.
     */
    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        System.out.printf("%s skipped%n", description.getMethodName());
    }

    /**
     * Invoked when a test is about to start
     */
    @Override
    protected void starting(Description description) {
        System.out.printf("%s is starting%n", description.getMethodName());
    }

    /**
     * Invoked when a test method finishes (whether passing or failing)
     */
    @Override
    protected void finished(Description description) {
        System.out.printf("%s finished%n", description.getMethodName());
    }
}
