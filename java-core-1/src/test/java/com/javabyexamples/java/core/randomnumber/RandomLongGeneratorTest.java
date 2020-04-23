package com.javabyexamples.java.core.randomnumber;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RandomLongGeneratorTest {

    private final RandomLongGenerator generator = new RandomLongGenerator();

    @Test
    public void testRandomUsingCommons() {
        execute(0, Long.MAX_VALUE, () -> generator.randomUsingCommons());
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
        execute(0, Long.MAX_VALUE, () -> generator.randomUsingRandom());
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
        execute(0, Long.MAX_VALUE, () -> generator.randomUsingCommonsMath());
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
        execute(0, Long.MAX_VALUE, () -> generator.randomUsingMath());
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
        execute(0, Long.MAX_VALUE, () -> generator.randomUsingStreams());
    }

    @Test
    public void testRandomUsingStreams_WithRange() {
        execute(0, 100, () -> generator.randomUsingStreams(0, 100));
    }

    @Test
    public void testRandomUsingStreams_ThenVerifyOccurrences() {
        executeThenVerifyOccurrences(5, 15, () -> generator.randomUsingStreams(5, 15));
    }

    private void executeThenVerifyOccurrences(long minInclusive, long maxExclusive,
      Supplier<Long> supplier) {
        final Map<Long, Boolean> occurrences = new HashMap<>();
        IntStream.range(0, 10000)
          .mapToObj(i -> {
              final Long aLong = supplier.get();
              occurrences.put(aLong, true);
              return aLong;
          })
          .filter(number -> number < minInclusive || number >= maxExclusive)
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not in the range: " + failure));

        LongStream.range(minInclusive, maxExclusive)
          .filter(occurrence -> !occurrences.containsKey(occurrence))
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not occurred: " + failure));
    }

    private void execute(long minInclusive, long maxExclusive, Supplier<Long> supplier) {
        execute(10000, minInclusive, maxExclusive, supplier);
    }

    private void execute(long times, long minInclusive, long maxExclusive,
      Supplier<Long> supplier) {
        LongStream.range(0, times)
          .mapToObj(i -> supplier.get())
          .filter(number -> number < minInclusive || number >= maxExclusive)
          .findFirst()
          .ifPresent(failure -> Assertions.fail("Not in the range: " + failure));
    }
}
