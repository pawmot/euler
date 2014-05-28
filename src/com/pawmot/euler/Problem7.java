package com.pawmot.euler;

import static com.pawmot.euler.utils.lazySeqs.factories.LongSeqs.primes;

public class Problem7 implements ProblemSolution {
    @Override
    public void printSolution() {
        long t1 = System.nanoTime();
        Long p = primes().nth(10000);
        long t2 = System.nanoTime();

        System.out.println("The 10001th prime is " + p);
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }
}
