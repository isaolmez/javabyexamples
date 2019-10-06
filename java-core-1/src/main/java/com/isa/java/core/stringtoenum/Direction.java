package com.isa.java.core.stringtoenum;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east");

    private static Map<String, Direction> nameToValue;

    static {
        nameToValue = Stream.of(values()).collect(Collectors.toMap(Direction::getName, Function.identity()));
    }

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Direction fromValueVersion1(String givenName) {
        for (Direction direction : values()) {
            if (direction.name.equals(givenName)) {
                return direction;
            }
        }

        return null;
    }

    public static Direction fromValueVersion2(String givenName) {
        return Stream.of(values())
                     .filter(direction -> direction.name.equals(givenName))
                     .findFirst()
                     .orElse(null);
    }

    public static Direction fromValueVersion3(String givenName) {
        return nameToValue.get(givenName);
    }
}
