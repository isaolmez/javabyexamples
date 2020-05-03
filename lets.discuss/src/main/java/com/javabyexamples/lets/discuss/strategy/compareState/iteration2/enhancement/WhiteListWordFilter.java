package com.javabyexamples.lets.discuss.strategy.compareState.iteration2.enhancement;


public class WhiteListWordFilter implements WordFilter {

    @Override
    public boolean filter(String word, WordFilterOptions wordFilterOptions) {
        return wordFilterOptions.getWhiteList().contains(word);
    }
}
