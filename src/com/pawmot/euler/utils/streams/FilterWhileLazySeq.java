package com.pawmot.euler.utils.streams;

import java.util.function.Predicate;

class FilterWhileLazySeq<T> implements LazySeq<T> {
    private final LazySeq<T> filtered;
    private final Predicate<T> filter;

    public FilterWhileLazySeq(LazySeq<T> filtered, Predicate<T> filter) {
        this.filtered = filtered;
        this.filter = filter;
    }

    @Override
    public T head() {
        return filtered.head();
    }

    @Override
    public LazySeq<T> tail() {
        LazySeq<T> tail = this.filtered.tail();
        if(!filter.test(tail.head())) return new Empty<>();
        return new FilterWhileLazySeq<>(tail, filter);
    }

    @Override
    public boolean isEmpty() {
        return filtered.isEmpty();
    }
}
