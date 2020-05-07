package com.javabyexamples.java.concurrency.common.runner;

public class RunnerAction {

    private final int executeCount;

    private final Runnable action;

    private RunnerAction(int executeCount, Runnable action) {
        this.executeCount = executeCount;
        this.action = action;
    }

    public int getExecuteCount() {
        return executeCount;
    }

    public Runnable getAction() {
        return action;
    }

    public static MementoBuilder builder(Runner.Builder builder) {
        return new MementoBuilder(builder);
    }

    public static class MementoBuilder {

        private int executeCount;

        private Runnable action;

        private final Runner.Builder builder;

        public MementoBuilder(Runner.Builder builder) {
            this.builder = builder;
        }

        public MementoBuilder executionCount(int executeCount) {
            this.executeCount = executeCount;
            return this;
        }

        public MementoBuilder action(Runnable action) {
            this.action = action;
            return this;
        }

        public Runner.Builder added() {
            builder.addAction(new RunnerAction(executeCount, action));
            return builder;
        }
    }
}
