package com.javabyexamples.java.core.randomnumber;

import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomDoubleGenerator {

    public double randomUsingRandom(double minInclusive, double maxExclusive) {
        final Random random = new Random();
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + (random.nextDouble() * (maxExclusive - minInclusive));
    }

    public double randomUsingRandom() {
        return randomUsingRandom(0, Double.MAX_VALUE);
    }

    public double randomUsingStreams(double minInclusive, double maxExclusive) {
        final Random random = new Random();
        return random.doubles(minInclusive, maxExclusive).findFirst().getAsDouble();
    }

    public double randomUsingStreams() {
        return randomUsingStreams(0, Double.MAX_VALUE);
    }

    public double randomUsingMath(double minInclusive, double maxExclusive) {
        if (minInclusive == maxExclusive) {
            return minInclusive;
        }

        return minInclusive + (Math.random() * (maxExclusive - minInclusive));
    }

    public double randomUsingMath() {
        return randomUsingMath(0, Double.MAX_VALUE);
    }

    public double randomUsingCommons(double minInclusive, double maxExclusive) {
        return RandomUtils.nextDouble(minInclusive, maxExclusive);
    }

    public double randomUsingCommons() {
        return RandomUtils.nextDouble();
    }

    public double randomUsingCommonsMath(double minInclusive, double maxExclusive) {
        final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.nextUniform(minInclusive, maxExclusive - 1, true);
    }

    public double randomUsingCommonsMath() {
        return randomUsingCommonsMath(0, Double.MAX_VALUE);
    }
}
