package com.javabyexamples.java.concurrency.buildingblocks.cache.example1;

import com.javabyexamples.java.concurrency.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.common.annotation.GuardedBy;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CacheWithSynchronized<K, V> implements ComputableCache<K, V> {

    @GuardedBy("this")
    private final Map<K, V> cache = new HashMap<>();

    private final Computable<K, V> delegate;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public CacheWithSynchronized(Computable<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public synchronized V compute(K arg) {
        V result = cache.get(arg);
        if (result == null) {
            result = delegate.compute(arg);
            cache.put(arg, result);
            putCount.getAndIncrement();
        }

        return result;
    }

    @Override
    public Map<K, V> getCache() {
        return cache;
    }

    @Override
    public int getPutCount() {
        return putCount.get();
    }
}
