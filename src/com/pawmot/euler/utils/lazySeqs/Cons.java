package com.pawmot.euler.utils.lazySeqs;

import java.util.function.Supplier;

public class Cons<T> implements LazySeq<T> {
    private final T head;

    private final Supplier<LazySeq<T>> tail;

    public Cons(T head, Supplier<LazySeq<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public LazySeq<T> tail() {
        return this.tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
