package com.pawmot.euler.utils.streams;

import java.util.function.Predicate;

class FilteredLazySeq<T> implements LazySeq<T> {
    private LazySeq<T> filtered;
    private final Predicate<T> filter;

    public FilteredLazySeq(LazySeq<T> filtered, Predicate<T> filter) {
        this.filtered = filtered;
        this.filter = filter;
    }

    @Override
    public T head() {
        return filtered.head();
    }

    @Override
    public LazySeq<T> tail() {
        return create(filtered.tail(), filter);
    }

    @Override
    public boolean isEmpty() {
        return filtered.isEmpty();
    }

    static <U> LazySeq<U> create(LazySeq<U> filtered, Predicate<U> filter) {
        LazySeq<U> next = applyFilter(filtered, filter);
        if(next.isEmpty()) return new Empty<>();
        else return new FilteredLazySeq<>(next, filter);
    }

    private static <U> LazySeq<U> applyFilter(LazySeq<U> filtered, Predicate<U> filter) {
        while(!filtered.isEmpty() && !filter.test(filtered.head())) {
            filtered = filtered.tail();
        }

        return filtered;
    }
}
