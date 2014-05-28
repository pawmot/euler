package com.pawmot.euler.utils.lazySeqs.factories;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

public final class IntSeqs {
    private IntSeqs() { }

    public static LazySeq<Integer> from(int n) {
        return new Cons<>(n, () -> from(n+1));
    }

    public static LazySeq<Integer> range(int startInclusive, int endExclusive) {
        return startInclusive < endExclusive ?
            new Cons<>(startInclusive, () -> range(startInclusive+1, endExclusive)) :
            new Empty<>();
    }

    public static LazySeq<Integer> fibonacci(final int f1, final int f2) {
        return new Cons<>(f1, () -> fibonacci(f2, f1+f2));
    }
}
