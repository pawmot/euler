package com.pawmot.euler;

import com.pawmot.euler.utils.BitArray;

public class Problem10 implements ProblemSolution {

    @Override
    public void printSolution() {
        int n = 2000000;
        BitArray sieve = new BitArray(n+1, true);

        long sum = 0;
        for(int i = 2; i <= n; i++) {
            if(sieve.get(i)) {
                sum += i;
                for(int j = 2*i; j < n; j+=i) {
                    sieve.set(j, false);
                }
            }
        }

        System.out.println("The sum of all primes lesser than 2,000,000 is " + sum);
    }
}
