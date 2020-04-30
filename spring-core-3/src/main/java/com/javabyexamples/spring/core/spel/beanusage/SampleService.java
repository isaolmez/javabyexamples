package com.javabyexamples.spring.core.spel.beanusage;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SampleService {

    @Value("#{ systemProperties['user.home']}")
    private String userHome;

    @Value("#{ @randomService.randomName()}")
    private String randomName;

    @Value("#{ new java.util.Random().nextInt(100)}")
    private String randomNumber;

    @Value("#{ {1,2,3} }")
    private List<Integer> numbers;

    @Value("#{ {'a','b','c'} }")
    private List<String> letters;

    @Value("#{ new String[]{'a','b','c'} }")
    private String[] letterArray;

    @Value("#{ {1:'one',2:'two',3:'three'} }")
    private Map<Integer, String> numberMap;

    @Value("#{ @randomService.randomNumber() < 50 ? '0':'1' }")
    private String diceRoll;

    @Value("#{ @randomService.randomName() ?:'fixed' }")
    private String nullCheck;

    @PostConstruct
    public void init() {
        System.out.println(userHome);
        System.out.println(randomName);
        System.out.println(randomNumber);

        System.out.println(numbers);
        System.out.println(letters);
        System.out.println(letterArray);
        System.out.println(numberMap);

        System.out.println(diceRoll);
        System.out.println(nullCheck);
    }
}
