package com.javabyexamples.java.concurrency.advanced.composing.delegation.single;

import com.javabyexamples.java.concurrency.advanced.common.annotation.CannotLeak;
import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegated;
import com.javabyexamples.java.concurrency.advanced.common.annotation.Delegating;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Delegating
@ThreadSafe
public class DelegatingSnapshotVehicleTracker {

    @Delegated
    private final ConcurrentMap<String, ImmutablePoint> locations;

    public DelegatingSnapshotVehicleTracker(Map<String, ImmutablePoint> points) {
        locations = new ConcurrentHashMap<>(points);
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
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }
}
