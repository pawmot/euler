package com.pawmot.euler;

import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.fibonacci;

public class Problem2 implements ProblemSolution {
    @Override
    public void printSolution() {
        Long sum = fibonacci(1, 2)
                .filterWhile(n -> n <= 4000000)
                .filter(n -> n % 2 == 0)
                .foldLeft((a, b) -> a + b, 0L);

        System.out.println("The sum of even fibonacci numbers not exceeding 4M is " + sum);
    }
}
