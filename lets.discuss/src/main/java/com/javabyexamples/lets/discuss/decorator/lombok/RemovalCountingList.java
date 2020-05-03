package com.javabyexamples.lets.discuss.decorator.lombok;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.experimental.Delegate;


public class RemovalCountingList<E> implements List<E> {

    @Delegate(excludes = ExcludedListMethods.class)
    private final List<E> delegate;

    private final AtomicInteger removalCount = new AtomicInteger();

    public RemovalCountingList(List<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public E remove(int index) {
        System.out.println("Removal count: " + removalCount.incrementAndGet());
        return delegate.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = delegate.remove(o);
        if (isRemoved) {
            System.out.println("Removal count: " + removalCount.incrementAndGet());
        }

        return isRemoved;
    }

    /**
     * Excluded methods that Lombok will not implement, we will implement/override these methods.
     */
    private abstract class ExcludedListMethods {

        public abstract E remove(int index);

        public abstract boolean remove(Object o);
    }
}
