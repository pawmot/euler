package com.pawmot.euler;

import com.pawmot.euler.utils.BitArray;

import static com.pawmot.euler.utils.lazySeqs.factories.LongSeqs.primes;

public class Problem10 implements ProblemSolution {

    @Override
    public void printSolution() {
        long t1 = System.nanoTime();
        int n = 2000000;
        long sum = inlineBitArraySieveSummation(n);
        long t2 = System.nanoTime();

        System.out.println("The sum of all primes lesser than 2,000,000 is " + sum);
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }

    // 25-30ms
    private long bitArraySieveSummation(int n) {
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

        return sum;
    }

    // 18-22ms
    private long inlineBitArraySieveSummation(int n) {
        int n1 = n+1;
        int[] data = new int[n1 % 32 == 0 ? n1/32 : n1/32 + 1];

        long sum = 0;
        for(int i = 2; i <= n1; i++) {
            int k = i >> 5;
            int m = i-k;

            int c = 1 << m;
            if((data[k] & c) != c) {
                sum += i;
                for(int j = 2*i; j <= n1; j+=i) {
                    int k1 = j >> 5;
                    int m1 = j-k1;
                    data[k1] |= 1 << m1;
                }
            }
        }

        return sum;
    }

    // 15-20ms
    private long arraySieveSummation(int n) {
        boolean[] sieve = new boolean[n+1];

        long sum = 0;
        for(int i = 2; i <= n; i++) {
            if(!sieve[i]) {
                sum += i;
                for(int j = 2*i; j < n; j+=i) {
                    sieve[j] = true;
                }
            }
        }

        return sum;
    }

    // WAY too slow - probably the primes sequence needs to be faster.
    private long lazySeqSummation(int n) {
        return primes().filterWhile(p -> p < n).reduceLeft((a, b) -> a+b);
    }
}
