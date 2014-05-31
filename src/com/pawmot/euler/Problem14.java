package com.pawmot.euler;

import com.pawmot.euler.utils.lazySeqs.Cons;
import com.pawmot.euler.utils.lazySeqs.Empty;
import com.pawmot.euler.utils.lazySeqs.LazySeq;
import com.pawmot.euler.utils.memoization.Memoizer1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

import static com.pawmot.euler.utils.lazySeqs.factories.IntSeqs.range;

public class Problem14 implements ProblemSolution {
    @Override
    public void printSolution() {
        LengthCalculator lc = new LengthCalculator();

        // Straightforward solution takes ca. 8800 ms
        // Solution with memoization takes 800-1500ms
        long t1 = System.nanoTime();
        //Long startOfLongest = range(1, 1000000).map(this::collatz).reduceLeft((a, b) -> a.length32() > b.length32() ? a : b).nth(0);
        Long startOfLongest = range(1, 1000000).map(this::collatz).reduceLeft((a, b) -> lc.calculateLength(a) > lc.calculateLength(b) ? a : b).nth(0);
        long t2 = System.nanoTime();

        System.out.println("The starting number of the longest Collatz sequence that is under 1000000 is " + startOfLongest);
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }

    private LazySeq<Long> collatz(long n) {
        return new Cons<>(n, () -> n == 1 ? new Empty<>() : collatz(n % 2 == 0 ? n/2 : 3*n + 1));
    }

    private class LengthCalculator {
        Map<Long, Integer> lMap = new HashMap<>();

        public int calculateLength(LazySeq<Long> ls) {
            // this works for the example data (1 - 999,999) with normal recursion, but the Stack approach is safer under unknown conditions - no risk of getting a StackOverflowError.
            Stack<LazySeq<Long>> s = new Stack<>();
            int l = 0;
            int stoppedAt = 0;
            while(!ls.isEmpty()) {
                if(lMap.containsKey(ls.head())) {
                    int fromMap = lMap.get(ls.head());
                    stoppedAt = fromMap;
                    l += fromMap;
                    break;
                }

                l++;
                s.push(ls);
                ls = ls.tail();
            }

            while(!s.empty()) {
                lMap.put(s.pop().head(), ++stoppedAt);
            }

            return l;
        }
    }
}
