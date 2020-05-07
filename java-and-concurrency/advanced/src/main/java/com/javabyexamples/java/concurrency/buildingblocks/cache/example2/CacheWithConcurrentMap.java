package com.javabyexamples.java.concurrency.buildingblocks.cache.example2;

import com.javabyexamples.java.concurrency.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CacheWithConcurrentMap<K, V> implements ComputableCache<K, V> {

    @Delegated
    private final Map<K, V> cache = new ConcurrentHashMap<>();

    private final Computable<K, V> delegate;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public CacheWithConcurrentMap(Computable<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public V compute(K arg) {
        return cache.compute(arg, (k, v) -> {
            if (v == null) {
                v = delegate.compute(arg);
                putCount.getAndIncrement();
            }

            return v;
        });
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
