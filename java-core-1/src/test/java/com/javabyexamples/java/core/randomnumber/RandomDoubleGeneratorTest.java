package com.javabyexamples.java.core.randomnumber;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RandomDoubleGeneratorTest {

    private final RandomDoubleGenerator generator = new RandomDoubleGenerator();

    @Test
    public void testRandomUsingCommons() {
        execute(0, Double.MAX_VALUE, () -> generator.randomUsingCommons());
    }

    @Test
    public void testRandomUsingCommons_WithRange() {
        execute(0, 100, () -> generator.randomUsingCommons(0, 100));
    }

    @Test
    public void testRandomUsingJdk() {
        execute(0, Double.MAX_VALUE, () -> generator.randomUsingRandom());
    }

    @Test
    public void testRandomUsingJdk_WithRange() {
        execute(0, 100, () -> generator.randomUsingRandom(0, 100));
    }

    @Test
    public void testRandomUsingCommonsMath() {
        execute(0, Double.MAX_VALUE, () -> generator.randomUsingCommonsMath());
    }

    @Test
    public void testRandomUsingCommonsMath_WithRange() {
        execute(0, 100, () -> generator.randomUsingCommonsMath(0, 100));
    }

    @Test
    public void testRandomUsingMath() {
        execute(0, Double.MAX_VALUE, () -> generator.randomUsingMath());
    }

    @Test
    public void testRandomUsingMath_WithRange() {
        execute(0, 100, () -> generator.randomUsingMath(0, 100));
    }

    @Test
    public void testRandomUsingStreams() {
        execute(0, Double.MAX_VALUE, () -> generator.randomUsingStreams());
    }

    @Test
    public void testRandomUsingStreams_WithRange() {
        execute(0, 100, () -> generator.randomUsingStreams(0, 100));
    }

    private void execute(double minInclusive, double maxExclusive, Supplier<Double> supplier) {
        execute(10000, minInclusive, maxExclusive, supplier);
    }

    private void execute(int times, double minInclusive, double maxExclusive,
      Supplier<Double> supplier) {
        IntStream.range(0, times)
          .mapToObj(i -> supplier.get())
          .filter(number -> number < minInclusive || number >= maxExclusive)
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not in the range: " + failure));
    }
}
