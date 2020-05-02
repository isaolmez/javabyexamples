package com.isa.java.test.testng.general;

import org.testng.annotations.Test;

public class GroupTest {

    @Test(groups = "football")
    public void shouldPlayFootball() {

    }

    @Test(groups = "basketball")
    public void shouldPlayBasketball() {

    }

}
