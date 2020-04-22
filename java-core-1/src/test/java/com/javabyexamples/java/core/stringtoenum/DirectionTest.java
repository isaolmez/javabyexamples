package com.javabyexamples.java.core.stringtoenum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void shouldConvertFromEnumConstant() {
        final Direction direction = Direction.valueOf("NORTH");

        assertEquals(Direction.NORTH, direction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConvertFromEnumConstant() {
        final Direction direction = Direction.valueOf("north");

        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void shouldConvertFromName_Version1() {
        final Direction direction = Direction.fromValueVersion1("north");

        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void shouldConvertFromName_Version2() {
        final Direction direction = Direction.fromValueVersion2("north");

        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void shouldConvertFromName_Version3() {
        final Direction direction = Direction.fromValueVersion3("north");

        assertEquals(Direction.NORTH, direction);
    }

}
