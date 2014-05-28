package com.pawmot.euler;

import static com.pawmot.euler.utils.lazySeqs.factories.LongSeqs.from;
import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.range;

public class Problem5 implements ProblemSolution {
    @Override
    public void printSolution() {
        // TODO: this solution is (extremely) naive - can be improved to be much more efficient
        Long result = from(1).firstOrNull(n -> range(1, 21).all(d -> n % d == 0));

        System.out.println("The smallest positive number evenly divisible by all numbers from 1 to 20 (inclusive) is " + result);
    }
}
