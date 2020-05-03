package com.javabyexamples.lets.discuss.strategy.compareState.iteration2.enhancement;

import java.util.List;

public class WordFilterOptions {

    private List<String> whiteList;
    private List<String> blackList;

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }
}
