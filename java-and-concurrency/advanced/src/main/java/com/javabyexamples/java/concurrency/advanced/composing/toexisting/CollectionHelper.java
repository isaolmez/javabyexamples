package com.javabyexamples.java.concurrency.advanced.composing.toexisting;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface CollectionHelper {

    void putIfAbsent(String value);

    List<String> getList();

    default Set<String> getDuplicates() {
        Set<String> duplicates = new HashSet<>();
        Collections.sort(getList());
        String previous = getList().get(0);
        for (int i = 1; i < getList().size(); i++) {
            String current = getList().get(i);
            if (current.equals(previous)) {
                duplicates.add(current);
            } else {
                previous = current;
            }
        }

        return duplicates;
    }
}
