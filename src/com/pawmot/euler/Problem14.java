package com.pawmot.euler;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.range;

public class Problem14 implements ProblemSolution {
    @Override
    public void printSolution() {
        // TODO: check if length memoization would improve the run time (currently ca. 8800ms)
        long t1 = System.nanoTime();
        Long startOfLongest = range(1, 1000000).map(this::collatz).reduceLeft((a, b) -> a.length32() > b.length32() ? a : b).nth(0);
        long t2 = System.nanoTime();

        System.out.println("The starting number of the longest Collatz sequence that is under 1000000 is " + startOfLongest);
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }

    private LazySeq<Long> collatz(long n) {
        return new Cons<>(n, () -> n == 1 ? new Empty<>() : collatz(n % 2 == 0 ? n/2 : 3*n + 1));
    }
}
