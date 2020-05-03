package com.javabyexamples.java.concurrency.advanced.composing.instanceconfinement;

import com.javabyexamples.java.concurrency.advanced.common.annotation.CannotLeak;
import com.javabyexamples.java.concurrency.advanced.common.annotation.GuardedBy;
import com.javabyexamples.java.concurrency.advanced.common.annotation.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    @CannotLeak
    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    @CannotLeak
    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> map) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : map.keySet()) {
            result.put(id, new MutablePoint(map.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
} 
