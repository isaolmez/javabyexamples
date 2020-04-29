package com.javabyexamples.spring.core.scopeddependency.servicelocator;

public interface CounterFactory {

    Counter getCounter();

    Counter getCounter(String name);
}
