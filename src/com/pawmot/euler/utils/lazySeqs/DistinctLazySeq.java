package com.pawmot.euler.utils.lazySeqs;

import java.util.HashSet;
import java.util.Set;

class DistinctLazySeq<T> implements LazySeq<T> {
    private final LazySeq<T> filtered;
    private final Set<T> elementsUpToThis;

    private DistinctLazySeq(LazySeq<T> filtered, Set<T> elementsUpToThis) {
        this.filtered = filtered;
        this.elementsUpToThis = elementsUpToThis;
    }

    @Override
    public T head() {
        return this.filtered.head();
    }

    @Override
    public LazySeq<T> tail() {
        return create(this.filtered.tail(), elementsUpToThis);
    }

    @Override
    public boolean isEmpty() {
        return this.filtered.isEmpty();
    }

    public static <U> LazySeq<U> create(LazySeq<U> filtered, Set<U> elementsUpToThis) {
        LazySeq<U> next = findNextDistinct(filtered, elementsUpToThis);
        if(next.isEmpty()) return new Empty<>();

        Set<U> els = new HashSet<>(elementsUpToThis);
        els.add(next.head());

        return new DistinctLazySeq<>(next, els);
    }

    private static <U> LazySeq<U> findNextDistinct(LazySeq<U> filtered, Set<U> elementsUpToThis) {
        while(!filtered.isEmpty() && elementsUpToThis.contains(filtered.head())) {
            filtered = filtered.tail();
        }

        return filtered;
    }
}
