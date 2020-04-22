package com.javabyexamples.java.core.securerandom;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class SecureRandomOperationsTest {

    private SecureRandomOperations secureRandomOperations = new SecureRandomOperations();

    @Test
    public void testRandom() {
        final int random1 = secureRandomOperations.getRandom();
        final int random2 = secureRandomOperations.getRandom();

        System.out.println(random1);
        System.out.println(random2);
        assertThat(random1).isNotEqualTo(random2);
    }

    @Test
    public void testRandom_WhenSeedIsGiven() {
        final long seed = 100L;
        final int random1 = secureRandomOperations.getRandomWithSeed(seed);
        final int random2 = secureRandomOperations.getRandomWithSeed(seed);

        System.out.println(random1);
        System.out.println(random2);
        assertThat(random1).isEqualTo(random2);
    }

    @Test
    public void testSecureRandom_WhenNativePRNG() throws NoSuchAlgorithmException {
        final long seed = 100L;
        final int random1 = secureRandomOperations.getSecureRandom_NativePRNG(seed);
        final int random2 = secureRandomOperations.getSecureRandom_NativePRNG(seed);

        System.out.println(random1);
        System.out.println(random2);
        assertThat(random1).isNotEqualTo(random2);
    }

    @Test
    public void testSecureRandom_WhenNativePRNGNonBlocking() throws NoSuchAlgorithmException {
        final long seed = 100L;
        final int random1 = secureRandomOperations.getSecureRandom_NativePRNGNonBlocking(seed);
        final int random2 = secureRandomOperations.getSecureRandom_NativePRNGNonBlocking(seed);

        System.out.println(random1);
        System.out.println(random2);
        assertThat(random1).isNotEqualTo(random2);
    }

    @Test
    public void testSecureRandom_WhenSHA1PRNG() throws NoSuchAlgorithmException {
        final long seed = 100L;
        final int random1 = secureRandomOperations.getSecureRandom_SHA1PRNG(seed);
        final int random2 = secureRandomOperations.getSecureRandom_SHA1PRNG(seed);

        System.out.println(random1);
        System.out.println(random2);
        assertThat(random1).isEqualTo(random2);
    }
}
