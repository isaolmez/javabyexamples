package com.javabyexamples.lets.discuss.strategy.compareState.iteration3;

import java.util.Collections;
import java.util.List;

public class WhiteListWordFilter implements WordFilter {

    private final List<String> whiteList;

    public WhiteListWordFilter(List<String> whiteList) {
        this.whiteList = Collections.unmodifiableList(whiteList);
    }

    @Override
    public boolean filter(String word) {
        return whiteList.contains(word);
    }
}
