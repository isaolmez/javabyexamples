package com.javabyexamples.java.concurrency.advanced.composing.toexisting.differentlocks;

import com.javabyexamples.java.concurrency.advanced.common.annotation.NotThreadSafe;
import com.javabyexamples.java.concurrency.advanced.composing.toexisting.CollectionHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NotThreadSafe
public class DifferentLockCollectionHelper implements CollectionHelper {

    private List<String> list = Collections.synchronizedList(new ArrayList<>());

    public void putIfAbsent(String value) {
        synchronized (this) {
            if (!list.contains(value)) {
                list.add(value);
            }
        }
    }

    @Override
    public List<String> getList() {
        return list;
    }
}
