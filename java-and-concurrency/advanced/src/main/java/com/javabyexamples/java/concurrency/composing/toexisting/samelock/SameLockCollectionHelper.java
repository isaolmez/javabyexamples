package com.javabyexamples.java.concurrency.composing.toexisting.samelock;

import com.javabyexamples.java.concurrency.common.annotation.ThreadSafe;
import com.javabyexamples.java.concurrency.composing.toexisting.CollectionHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ThreadSafe
public class SameLockCollectionHelper implements CollectionHelper {

    private final List<String> list = Collections.synchronizedList(new ArrayList<>());

    public void putIfAbsent(String value) {
        synchronized (list) {
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
