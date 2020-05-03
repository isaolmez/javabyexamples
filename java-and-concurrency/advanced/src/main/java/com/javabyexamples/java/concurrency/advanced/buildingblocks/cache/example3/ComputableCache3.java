package com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.example3;

import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.Computable;
import com.javabyexamples.java.concurrency.advanced.buildingblocks.cache.ComputableCache;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@ThreadSafe
public class ComputableCache3<A, V> implements ComputableCache<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    private final AtomicInteger putCount = new AtomicInteger(0);

    public ComputableCache3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) {

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
    public Map<A, V> getCache() {
        return cache.entrySet().stream()
          .collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
              try {
                  return entry.getValue().get();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (ExecutionException e) {
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
