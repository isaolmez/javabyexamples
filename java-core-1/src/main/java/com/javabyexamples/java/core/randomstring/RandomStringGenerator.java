package com.javabyexamples.java.core.randomstring;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;

public class RandomStringGenerator {

    public void randomUsingPlain(int length) {
        final Random random = new Random();
        final byte[] array = new byte[length];
        random.nextBytes(array);
        final String generated = new String(array, StandardCharsets.UTF_8);

        System.out.println(generated);
    }

    public void randomUsingStreams(int length) {
        final int start = '0';
        final int end = 'z';
        final Random random = new Random();
        final String generated = random.ints(start, end + 1)
          .filter(i -> Character.isLetter(i) || Character.isDigit(i))
          .limit(length)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        System.out.println(generated);
    }

    public void randomUsingCommons(int length) {
        System.out.println(RandomStringUtils.random(length));
        System.out.println(RandomStringUtils.random(length, "abcdefghij"));
        System.out.println(RandomStringUtils.randomAlphabetic(length));
        System.out.println(RandomStringUtils.randomAlphanumeric(length));
        System.out.println(RandomStringUtils.randomNumeric(length));
    }

    public void randomUsingCommonsText(int length) {
        final org.apache.commons.text.RandomStringGenerator generatorWithRange = new org.apache.commons.text.RandomStringGenerator.Builder()
          .withinRange('0', 'z')
          .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
          .build();
        System.out.println(generatorWithRange.generate(length));

        final org.apache.commons.text.RandomStringGenerator generatorWithSelection = new org.apache.commons.text.RandomStringGenerator.Builder()
          .selectFrom("abcdefghij".toCharArray())
          .build();
        System.out.println(generatorWithSelection.generate(length));
    }

    public void randomUsingUuid() {
        System.out.println(UUID.randomUUID().toString());
    }

    public static void main(String[] args) {
        final RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        randomStringGenerator.randomUsingPlain(5);
        randomStringGenerator.randomUsingStreams(5);
        randomStringGenerator.randomUsingUuid();
        randomStringGenerator.randomUsingCommons(5);
        randomStringGenerator.randomUsingCommonsText(5);

    }
}
