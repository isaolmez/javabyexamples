package com.javabyexamples.java.concurrency.sharing.visibility;

import com.javabyexamples.java.concurrency.common.annotation.NonStaleData;
import com.javabyexamples.java.concurrency.common.annotation.StaleData;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;

public class ReadWriteSynchronization {

    @StaleData
    public class MutableInteger {

        private int value;

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }
    }

    @NonStaleData
    @ThreadSafe
    public class SynchronizedInteger {

        private int value;

        public synchronized int get() {
            return value;
        }

        public synchronized void set(int value) {
            this.value = value;
        }
    }

    @NonStaleData
    @ThreadSafe
    public class VolatileInteger {

        private volatile int value;

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }
    }
}
