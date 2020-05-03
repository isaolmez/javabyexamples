package com.javabyexamples.lets.discuss.decorator.lombok;

import java.util.ArrayList;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) {
        test(new RemovalCountingList<>(new ArrayList<>()));

        test(new RemovalCountingListV2<>(new ArrayList<>()));
    }

    private static void test(List<String> cities) {
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Tokyo");

        String removedCity = cities.remove(0);
        System.out.println("Removed city: " + removedCity);

        boolean isRemoved = cities.remove("Istanbul");
        System.out.println("Is removed?: " + isRemoved);
    }
}
