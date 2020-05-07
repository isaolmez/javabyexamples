package com.javabyexamples.java.concurrency.composing.delegation.single;

import com.javabyexamples.java.concurrency.common.annotation.CannotLeak;
import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class PublishingVehicleTracker {

    private final Map<String, SafeMutablePoint> locations;
    private final Map<String, SafeMutablePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafeMutablePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    @CannotLeak
    public Map<String, SafeMutablePoint> getLocations() {
        return Collections.unmodifiableMap(locations);
    }

    public SafeMutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException(
              "invalid vehicle name: " + id);
        }
        locations.get(id).set(x, y);
    }
}
