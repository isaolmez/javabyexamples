package com.javabyexamples.spring.core.spel.beanusage;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomService {

    public String randomName() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public int randomNumber() {
        return RandomUtils.nextInt(0, 100);
    }
}
