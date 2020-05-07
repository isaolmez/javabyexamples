package com.javabyexamples.java.concurrency.buildingblocks.cache;

import java.util.Map;

public interface ComputableCache<A, V> extends Computable<A, V> {

    Map<A, V> getCache();

    int getPutCount();
} 
