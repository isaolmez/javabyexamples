package com.javabyexamples.java.core.randomnumber;

import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomIntegerGenerator {

    public int randomUsingRandom(int minInclusive, int maxExclusive) {
        final Random random = new Random();
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + random.nextInt(maxExclusive - minInclusive);
    }

    public int randomUsingRandom() {
        return randomUsingRandom(0, Integer.MAX_VALUE);
    }

    public int randomUsingStreams(int minInclusive, int maxExclusive) {
        final Random random = new Random();
        return random.ints(minInclusive, maxExclusive).findFirst().getAsInt();
    }

    public int randomUsingStreams() {
        return randomUsingStreams(0, Integer.MAX_VALUE);
    }

    public int randomUsingMath(int minInclusive, int maxExclusive) {
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + (int) (Math.random() * (maxExclusive - minInclusive));
    }

    public int randomUsingMath() {
        return randomUsingMath(0, Integer.MAX_VALUE);
    }

    public int randomUsingCommons(int minInclusive, int maxExclusive) {
        return RandomUtils.nextInt(minInclusive, maxExclusive);
    }

    public int randomUsingCommons() {
        return RandomUtils.nextInt();
    }

    public int randomUsingCommonsMath(int minInclusive, int maxExclusive) {
        final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.nextInt(minInclusive, maxExclusive - 1);
    }

    public int randomUsingCommonsMath() {
        return randomUsingCommonsMath(0, Integer.MAX_VALUE);
    }
}
