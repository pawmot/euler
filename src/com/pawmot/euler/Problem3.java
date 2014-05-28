package com.pawmot.euler;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;

import static com.pawmot.euler.utils.lazySeqs.factories.LongSeqs.from;

public class Problem3 implements ProblemSolution {
    @Override
    public void printSolution() {
        long n = 600851475143L;
        LazySeq<Long> primeFactors = getPrimeFactors(n);
        Long largest = primeFactors.distinct().reduceLeft((a, b) -> a > b ? a : b);

        System.out.println(String.format("Largest prime factor of %1$d is %2$d", n, largest));
    }

    private LazySeq<Long> getPrimeFactors(long n) {
        if(n <= 1) return new Empty<>();

        long factor = getLowestPrimeFactor(n);

        return new Cons<>(factor, () -> getPrimeFactors(n/factor));
    }

    private long getLowestPrimeFactor(long n) {
        if(n <= 1) throw new IllegalArgumentException("Argument must be at least 2");
        return from(2).firstOrNull(d -> n % d == 0);
    }
}
