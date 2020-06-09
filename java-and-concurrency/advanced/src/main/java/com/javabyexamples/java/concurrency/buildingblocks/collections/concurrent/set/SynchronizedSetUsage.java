package com.javabyexamples.java.concurrency.buildingblocks.collections.concurrent.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SynchronizedSetUsage {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadedAccess multiThreadedAccess = new MultiThreadedAccess();
        multiThreadedAccess.doInsert(Collections.synchronizedSet(new HashSet<>()));
        multiThreadedAccess.doIterate(Collections.synchronizedSet(new HashSet<>()));
    }

    public static class CompoundActions {

        private final Set<Object> synchronizedSet = Collections.synchronizedSet(new HashSet<>());

        public void putIfAbsent(Object element) {
            synchronized (synchronizedSet) {
                if (!synchronizedSet.contains(element)) {
                    synchronizedSet.add(element);
                }
            }
        }
    }

    public static class Iterators {

        private final Set<Object> synchronizedSet = Collections.synchronizedSet(new HashSet<>());

        public void iterateWithLockHeld() {
            synchronized (synchronizedSet) {
                for (Object element : synchronizedSet) {
                    // Do work...
                }
            }
        }
    }
}
