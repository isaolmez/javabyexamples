package com.javabyexamples.java.concurrency.buildingblocks.cache.example3;

import com.javabyexamples.java.concurrency.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ThreadSafe
public class CacheWithConcurrentMapAndFuture<K, V> implements ComputableCache<K, V> {

    @Delegated
    private final Map<K, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<K, V> c;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public CacheWithConcurrentMapAndFuture(Computable<K, V> c) {
        this.c = c;
    }

    public V compute(final K arg) {
        cache.compute(arg, (key, value) -> {
            if (value == null) {
                FutureTask futureTask = new FutureTask<V>(() -> c.compute(arg));
                futureTask.run();
                putCount.getAndIncrement();
                return futureTask;
            }

            return value;
        });

        try {
            return cache.get(arg).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<K, V> getCache() {
        return cache.entrySet().stream()
          .collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
              try {
                  return entry.getValue().get();
              } catch (InterruptedException | ExecutionException e) {
                  e.printStackTrace();
              }

              return null;
          }));
    }

    @Override
    public int getPutCount() {
        return putCount.get();
    }
}
