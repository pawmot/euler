package com.pawmot.euler.utils.lazySeqs.factories;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

public final class LongSeqs {
    private LongSeqs() {}

    public static LazySeq<Long> from(long n) {
        return new Cons<>(n, () -> from(n+1));
    }

    public static LazySeq<Long> range(long startInclusive, long endExclusive) {
        return startInclusive < endExclusive ?
                new Cons<>(startInclusive, () -> range(startInclusive+1, endExclusive)) :
                new Empty<>();
    }

    public static LazySeq<Long> fibonacci(final long f1, final long f2) {
        return new Cons<>(f1, () -> fibonacci(f2, f1+f2));
    }
}
