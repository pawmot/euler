package com.pawmot.euler;

import com.pawmot.euler.utils.streams.Cons;
import com.pawmot.euler.utils.streams.Empty;
import com.pawmot.euler.utils.streams.LazySeq;

public class Problem1 implements ProblemSolution {
    @Override
    public void printSolution() {
        int sum = range(1, 1000)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .reduceLeft((a, b) -> a + b);
        System.out.println(sum);
    }

    private static LazySeq<Integer> range(final int startInclusive, final int endExclusive) {
        return startInclusive < endExclusive ?
                new Cons<>(startInclusive, () -> range(startInclusive+1, endExclusive)) :
                new Empty<>();
    }
}
