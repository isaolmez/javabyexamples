package com.javabyexamples.java.core.randomnumber;

import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomLongGenerator {

    public long randomUsingRandom(long minInclusive, long maxExclusive) {
        final Random random = new Random();
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + (long) (random.nextDouble() * (maxExclusive - minInclusive));
    }

    public long randomUsingRandom() {
        return randomUsingRandom(0, Long.MAX_VALUE);
    }

    public long randomUsingStreams(long minInclusive, long maxExclusive) {
        final Random random = new Random();
        return random.longs(minInclusive, maxExclusive).findFirst().getAsLong();
    }

    public long randomUsingStreams() {
        return randomUsingStreams(0, Long.MAX_VALUE);
    }

    public long randomUsingMath(long minInclusive, long maxExclusive) {
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + (long) (Math.random() * (maxExclusive - minInclusive));
    }

    public long randomUsingMath() {
        return randomUsingMath(0, Long.MAX_VALUE);
    }

    public long randomUsingCommons(long minInclusive, long maxExclusive) {
        return RandomUtils.nextLong(minInclusive, maxExclusive);
    }

    public long randomUsingCommons() {
        return RandomUtils.nextLong();
    }

    public long randomUsingCommonsMath(long minInclusive, long maxExclusive) {
        final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.nextLong(minInclusive, maxExclusive - 1);
    }

    public long randomUsingCommonsMath() {
        return randomUsingCommonsMath(0, Long.MAX_VALUE);
    }
}
