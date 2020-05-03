package com.javabyexamples.lets.discuss.strategy.compareState.iteration2;

import java.util.List;

public class WhiteListWordFilter implements WordFilter {

    @Override
    public boolean filter(String word, List<String> whiteList, List<String> blackList) {
        return whiteList.contains(word);
    }
}
