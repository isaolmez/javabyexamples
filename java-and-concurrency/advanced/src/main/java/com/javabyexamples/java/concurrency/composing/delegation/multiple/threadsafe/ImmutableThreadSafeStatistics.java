package com.javabyexamples.java.concurrency.composing.delegation.multiple.threadsafe;

import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.common.annotation.Immutable;
import com.javabyexamples.java.concurrency.common.annotation.InvariantHolds;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.composing.delegation.multiple.Statistics;

@Delegating
@ThreadSafe
public class ImmutableThreadSafeStatistics implements Statistics {

    @Delegated
    private volatile Counter counter = new Counter(1, 0);

    @InvariantHolds
    @Override
    public synchronized void update() {
        counter = counter.increment();
    }

    @Override
    public synchronized boolean invariantHolds() {
        return counter.invariantHolds();
    }

    @Override
    public void print() {
        System.out.printf("Current: %s, Previous: %s%n", counter.current, counter.previous);
    }

    @Immutable
    public static class Counter {

        private final int current;
        private final int previous;

        public Counter(int current, int previous) {
            this.current = current;
            this.previous = previous;
        }

        public Counter increment() {
            return new Counter(current + 1, previous + 1);
        }

        public boolean invariantHolds() {
            return current - previous == 1;
        }

        public int getCurrent() {
            return current;
        }

        public int getPrevious() {
            return previous;
        }
    }
}
