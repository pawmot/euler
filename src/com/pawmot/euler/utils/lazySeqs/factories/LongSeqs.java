package com.pawmot.euler.utils.lazySeqs.factories;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

import java.util.ArrayList;
import java.util.List;

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

    public static LazySeq<Long> primes() {
        ArrayList<Long> l = new ArrayList<>();
        l.add(2L);
        return new Cons<>(2L, () -> primeGenerator(l, 3L));
    }

    // TODO: This can be further optimised by restricting the tested numbers
    // TODO: This can be optimised by using an immutable List implementation
    private static LazySeq<Long> primeGenerator(List<Long> primesUpToThis, Long candidate) {
        boolean searching = true;
        while(searching) {

            long limit = (long) Math.sqrt(candidate);
            boolean passed = true;

            for(long p : primesUpToThis) {
                if(p > limit) break;
                if(candidate % p == 0) {
                    passed = false;
                    break;
                }
            }

            if(passed) {
                primesUpToThis.add(candidate);
                searching = false;
            }
            else {
                candidate += 2;
            }
        }

        List<Long> newPrimes = new ArrayList<>(primesUpToThis);
        newPrimes.add(candidate);
        long nextCandidate = candidate + 2;
        return new Cons<>(candidate, () -> primeGenerator(newPrimes, nextCandidate));
    }
}
