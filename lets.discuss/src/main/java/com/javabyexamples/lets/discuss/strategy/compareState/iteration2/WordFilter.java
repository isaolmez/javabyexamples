package com.javabyexamples.lets.discuss.strategy.compareState.iteration2;

import java.util.List;

public interface WordFilter {

    boolean filter(String word, List<String> whiteList, List<String> blackList);
}
