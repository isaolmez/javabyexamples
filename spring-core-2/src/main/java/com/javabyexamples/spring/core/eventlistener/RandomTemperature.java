package com.javabyexamples.spring.core.eventlistener;

import java.util.Random;

public class RandomTemperature {

    private static final Random random = new Random();

    public static int getTemperature() {
        return random.nextInt(50);
    }
}
