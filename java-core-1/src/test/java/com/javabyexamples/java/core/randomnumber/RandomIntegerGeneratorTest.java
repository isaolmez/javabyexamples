package com.javabyexamples.java.core.randomnumber;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.apache.commons.lang3.BooleanUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RandomIntegerGeneratorTest {

    private final RandomIntegerGenerator generator = new RandomIntegerGenerator();

    @Test
    public void testRandomUsingCommons() {
        execute(0, Integer.MAX_VALUE, () -> generator.randomUsingCommons());
    }

    @Test
    public void testRandomUsingCommons_WithRange() {
        execute(0, 100, () -> generator.randomUsingCommons(0, 100));
    }

    @Test
    public void testRandomUsingCommons_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingCommons(5, 15));
    }

    @Test
    public void testRandomUsingJdk() {
        execute(0, Integer.MAX_VALUE, () -> generator.randomUsingRandom());
    }

    @Test
    public void testRandomUsingJdk_WithRange() {
        execute(0, 100, () -> generator.randomUsingRandom(0, 100));
    }

    @Test
    public void testRandomUsingJdk_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingRandom(5, 15));
    }

    @Test
    public void testRandomUsingCommonsMath() {
        execute(0, Integer.MAX_VALUE, () -> generator.randomUsingCommonsMath());
    }

    @Test
    public void testRandomUsingCommonsMath_WithRange() {
        execute(0, 100, () -> generator.randomUsingCommonsMath(0, 100));
    }

    @Test
    public void testRandomUsingCommonsMath_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingCommonsMath(5, 15));
    }

    @Test
    public void testRandomUsingMath() {
        execute(0, Integer.MAX_VALUE, () -> generator.randomUsingMath());
    }

    @Test
    public void testRandomUsingMath_WithRange() {
        execute(0, 100, () -> generator.randomUsingMath(0, 100));
    }

    @Test
    public void testRandomUsingMath_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingMath(5, 15));
    }

    @Test
    public void testRandomUsingStreams() {
        execute(0, Integer.MAX_VALUE, () -> generator.randomUsingStreams());
    }

    @Test
    public void testRandomUsingStreams_WithRange() {
        execute(0, 100, () -> generator.randomUsingStreams(0, 100));
    }

    @Test
    public void testRandomUsingStreams_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingStreams(5, 15));
    }

    private void executeThenVerifyOccurrences(int minInclusive, int maxExclusive,
      Supplier<Integer> supplier) {
        final Boolean[] occurrences = new Boolean[maxExclusive - minInclusive];
        IntStream.range(0, 10000)
          .mapToObj(i -> {
              final Integer integer = supplier.get();
              occurrences[integer - minInclusive] = true;
              return integer;
          })
          .filter(number -> number < minInclusive || number >= maxExclusive)
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not in the range: " + failure));

        IntStream.range(minInclusive, maxExclusive)
          .filter(occurrence -> BooleanUtils.isNotTrue(occurrences[occurrence - minInclusive]))
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not occurred: " + failure));
    }

    private void execute(int minInclusive, int maxExclusive, Supplier<Integer> supplier) {
        execute(10000, minInclusive, maxExclusive, supplier);
    }

    private void execute(int times, int minInclusive, int maxExclusive,
      Supplier<Integer> supplier) {
        IntStream.range(0, times)
          .mapToObj(i -> supplier.get())
          .filter(number -> number < minInclusive || number >= maxExclusive)
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not in the range: " + failure));
    }
}
