package com.pawmot.euler.utils.lazySeqs;

import java.util.function.Supplier;

public class Cons<T> implements LazySeq<T> {
    private final T head;

    private final Supplier<LazySeq<T>> tailProvider;

    public Cons(T head, Supplier<LazySeq<T>> tailProvider) {
        this.head = head;
        this.tailProvider = tailProvider;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public LazySeq<T> tail() {
        return this.tailProvider.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
