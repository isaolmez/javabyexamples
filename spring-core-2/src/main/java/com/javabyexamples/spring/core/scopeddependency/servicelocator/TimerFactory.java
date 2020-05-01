package com.javabyexamples.spring.core.scopeddependency.servicelocator;

public interface TimerFactory {

    Timer getTimer();

    Timer getTimer(String name);
}
