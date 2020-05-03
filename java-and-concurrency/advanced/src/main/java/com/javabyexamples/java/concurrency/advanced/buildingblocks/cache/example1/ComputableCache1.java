package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example1;

import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.advanced.common.annotation.GuardedBy;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class ComputableCache1<A, V> implements ComputableCache<A, V> {

    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();

    private final Computable<A, V> computable;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public ComputableCache1(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) {
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
            putCount.getAndIncrement();
        }

        return result;
    }

    @Override
    public Map<A, V> getCache() {
        return cache;
    }

    @Override
    public int getPutCount() {
        return putCount.get();
    }
}
