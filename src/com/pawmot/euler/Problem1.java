package com.pawmot.euler;

import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.range;

public class Problem1 implements ProblemSolution {
    @Override
    public void printSolution() {
        int sum = range(1, 1000)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .reduceLeft((a, b) -> a + b);
        System.out.println(sum);
    }
}
