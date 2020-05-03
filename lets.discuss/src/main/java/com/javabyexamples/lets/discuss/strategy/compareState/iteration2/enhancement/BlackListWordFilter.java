package com.javabyexamples.lets.discuss.strategy.compareState.iteration2.enhancement;

public class BlackListWordFilter implements WordFilter {

    @Override
    public boolean filter(String word, WordFilterOptions wordFilterOptions) {
        return !wordFilterOptions.getBlackList().contains(word);
    }
}
