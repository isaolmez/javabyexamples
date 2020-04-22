package com.javabyexamples.java.core.securerandom;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomOperations {

    public int getRandom() {
        final Random random = new Random();
        return random.nextInt();
    }

    public int getRandomWithSeed(long seed) {
        final Random random = new Random(seed);
        return random.nextInt();
    }

    public void commonSecureRandomOperations() {
        final SecureRandom secureRandom = new SecureRandom();
        final byte[] bytes = new byte[10];
        secureRandom.nextBytes(bytes);
        final String randomString = new String(bytes, StandardCharsets.UTF_8);

        final int randomInteger = secureRandom.nextInt();
        final int randomBoundedInteger = secureRandom.nextInt(100);
        final long randomLong = secureRandom.nextLong();
        final double randomDouble = secureRandom.nextDouble();
    }

    public int getSecureRandom() {
        final SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt();
    }

    public int getSecureRandomWithSeed(long seed) {
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);
        return secureRandom.nextInt();
    }

    public int getSecureRandom_NativePRNG(long seed) throws NoSuchAlgorithmException {
        final SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");
        secureRandom.setSeed(seed);
        return secureRandom.nextInt();
    }

    public int getSecureRandom_SHA1PRNG(long seed) throws NoSuchAlgorithmException {
        final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed);
        return secureRandom.nextInt();
    }

    public int getSecureRandom_NativePRNGBlocking(long seed) throws NoSuchAlgorithmException {
        final SecureRandom secureRandom = SecureRandom.getInstance("NativePRNGBlocking");
        secureRandom.setSeed(seed);
        return secureRandom.nextInt();
    }

    public int getSecureRandom_NativePRNGNonBlocking(long seed) throws NoSuchAlgorithmException {
        final SecureRandom secureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
        secureRandom.setSeed(seed);
        return secureRandom.nextInt();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        final long seed = 20L;
        final SecureRandomOperations secureRandomOperations = new SecureRandomOperations();
        secureRandomOperations.commonSecureRandomOperations();

        System.out.println(secureRandomOperations.getRandom());
        System.out.println(secureRandomOperations.getRandomWithSeed(20L));

        System.out.println(secureRandomOperations.getSecureRandom());
        System.out.println(secureRandomOperations.getSecureRandomWithSeed(seed));
        System.out.println(secureRandomOperations.getSecureRandom_NativePRNG(seed));
        System.out.println(secureRandomOperations.getSecureRandom_SHA1PRNG(seed));
        System.out.println(secureRandomOperations.getSecureRandom_NativePRNGNonBlocking(seed));
    }
}
