package com.javabyexamples.lets.discuss.strategy.compareState.iteration3;

import java.util.Collections;
import java.util.List;

public class BlackListWordFilter implements WordFilter {

    private final List<String> blackList;

    public BlackListWordFilter(List<String> blackList) {
        this.blackList = Collections.unmodifiableList(blackList);
    }

    @Override
    public boolean filter(String word) {
        return !blackList.contains(word);
    }
}
