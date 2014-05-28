package com.pawmot.euler.utils.lazySeqs;

import java.util.function.Function;

class MappedLazySeq<T, U> implements LazySeq<U> {
    private final LazySeq<T> mapped;
    private final Function<T, U> f;

    public MappedLazySeq(LazySeq<T> mapped, Function<T, U> f) {
        this.mapped = mapped;
        this.f = f;
    }

    @Override
    public U head() {
        return f.apply(this.mapped.head());
    }

    @Override
    public LazySeq<U> tail() {
        return new MappedLazySeq<>(this.mapped.tail(), f);
    }

    @Override
    public boolean isEmpty() {
        return this.mapped.isEmpty();
    }
}
