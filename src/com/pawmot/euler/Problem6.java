package com.pawmot.euler;

import com.pawmot.euler.utils.lazySeqs.LazySeq;

import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.range;

public class Problem6 implements ProblemSolution {
    @Override
    public void printSolution() {
        int n = 100;

        LazySeq<Integer> r = range(1, n + 1);
        long sumOfSquares = r.map(i -> i * i).reduceLeft((a, b) -> a+b);

        int sum = r.reduceLeft((a, b) -> a+b);
        long squareOfSum = sum * sum;

        long diff = Math.abs(sumOfSquares - squareOfSum);

        System.out.println("The difference between the sum of the squared of the first 100 natural numbers and the square of their sum is " + diff);
    }
}
