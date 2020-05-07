package com.javabyexamples.java.concurrency.composing.delegation.single;

import com.javabyexamples.java.concurrency.common.annotation.CannotLeak;
import com.javabyexamples.java.concurrency.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Delegating
@ThreadSafe
public class DelegatingLiveVehicleTracker {

    @Delegated
    private final ConcurrentMap<String, ImmutablePoint> locations;

    private final Map<String, ImmutablePoint> unmodifiableMap;

    public DelegatingLiveVehicleTracker(Map<String, ImmutablePoint> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public ImmutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new ImmutablePoint(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }

    @CannotLeak
    public Map<String, ImmutablePoint> getLocations() {
        return unmodifiableMap;
    }
} 
