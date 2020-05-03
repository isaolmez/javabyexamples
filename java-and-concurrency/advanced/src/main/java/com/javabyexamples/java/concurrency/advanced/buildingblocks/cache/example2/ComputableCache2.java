package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example2;

import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class ComputableCache2<A, V> implements ComputableCache<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public ComputableCache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) {
        return cache.compute(arg, (k, v) -> {
            if (v == null) {
                v = c.compute(arg);
                putCount.getAndIncrement();
            }

            return v;
        });
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
