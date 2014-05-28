package com.pawmot.euler;

import com.pawmot.euler.utils.streams.Cons;
import com.pawmot.euler.utils.streams.LazySeq;

public class Problem2 implements ProblemSolution {
    @Override
    public void printSolution() {
        LazySeq<Integer> fib = fibonacci(1, 2)
                .filterWhile(f_n -> f_n <= 4000000)
                .filter(f_n -> f_n % 2 == 0);
        Long sum = fib.foldLeft((a, b) -> a + b, 0L);

        System.out.println("The sum of even fibonacci numbers not exceeding 4M is " + sum);
    }

    private LazySeq<Integer> fibonacci(final int f1, final int f2) {
        return new Cons<>(f1, () -> fibonacci(f2, f1+f2));
    }
}
