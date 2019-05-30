package com.javabyexamples.spring.core.usingautowired.wiremultiple;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodManager {

    @Autowired
    private GoodService[] goodServicesArray;

    @Autowired
    private List<GoodService> goodServicesList;

    @Autowired
    private Set<GoodService> goodServicesSet;

    @Autowired
    private Map<String, GoodService> goodServicesMap;

    public void list() {
        Arrays.stream(goodServicesArray).forEach(this::printClass);
        goodServicesList.forEach(this::printClass);
        goodServicesSet.forEach(this::printClass);
        goodServicesMap.forEach((key, value) -> System.out.printf("%s: %s%n", key, value.getClass().getSimpleName()));
    }

    private void printClass(GoodService goodService) {
        System.out.println(goodService.getClass().getSimpleName());
    }
}
