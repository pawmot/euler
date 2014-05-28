package com.pawmot.euler.utils.streams;

class BoundedLazySeq<T> implements LazySeq<T> {
    private final LazySeq<T> bounded;
    private final int bound;

    public BoundedLazySeq(LazySeq<T> bounded, int bound) {
        this.bounded = bounded;
        this.bound = bound;
    }

    @Override
    public T head() {
        return this.bounded.head();
    }

    @Override
    public LazySeq<T> tail() {
        if(bound == 1) return new Empty<>();
        return new BoundedLazySeq<>(this.bounded.tail(), bound - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.bounded.isEmpty();
    }
}
