package com.javabyexamples.java.concurrency.common.runner;

import com.javabyexamples.java.concurrency.common.ExecutorUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    private final int threadCount;

    private final List<RunnerAction> runnerActions;

    private Runner(int threadCount, List<RunnerAction> runnerActions) {
        this.threadCount = threadCount;
        this.runnerActions = runnerActions;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public List<RunnerAction> getRunnerActions() {
        return runnerActions;
    }

    public static Builder builder() {
        return new Builder();
    }

    private int getTotalExecutions() {
        return runnerActions.stream().mapToInt(RunnerAction::getExecuteCount).sum();
    }

    public void run() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(getTotalExecutions());

        runnerActions.forEach(runnerAction -> {
            for (int i = 0; i < runnerAction.getExecuteCount(); i++) {
                executorService.execute(() -> {
                      try {
                          start.await();
                          runnerAction.getAction().run();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      } finally {
                          done.countDown();
                      }
                  }
                );
            }
        });

        start.countDown();
        done.await();
        ExecutorUtils.shutdownAndAwaitTermination(executorService);
    }

    public static class Builder {

        private int threadCount = 1;

        private List<RunnerAction> runnerActions = new ArrayList<>();

        public Builder threadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder addAction(RunnerAction runnerAction) {
            runnerActions.add(runnerAction);
            return this;
        }

        public RunnerAction.MementoBuilder addAction() {
            return new RunnerAction.MementoBuilder(this);
        }

        public Runner build() {
            return new Runner(threadCount, runnerActions);
        }
    }
}
