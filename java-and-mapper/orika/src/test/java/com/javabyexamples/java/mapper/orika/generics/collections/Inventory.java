package com.javabyexamples.java.mapper.orika.generics.collections;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, ?> data = new HashMap<>();

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }
}
