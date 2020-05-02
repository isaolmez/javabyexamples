package com.javabyexamples.java.test.testng.general;

import java.util.Random;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @DataProvider(name = "numbers")
    private Object[][] numberProvider() {
        Random random = new Random();
        return new Object[][]{{random.nextInt()}, {random.nextInt()}, {random.nextInt()}};
    }

    @Test(dataProvider = "numbers")
    public void shouldRun(int number) {
        System.out.println("Data provided: " + number);
    }

}
