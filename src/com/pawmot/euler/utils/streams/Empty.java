package com.pawmot.euler.utils.streams;

import java.util.NoSuchElementException;

public class Empty<T> implements LazySeq<T> {
    @Override
    public T head() {
        throw new NoSuchElementException("Empty stream has no head");
    }

    @Override
    public LazySeq<T> tail() {
        throw new UnsupportedOperationException("Empty stream has no tail");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
