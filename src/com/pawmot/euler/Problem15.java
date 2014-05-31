package com.pawmot.euler;

import com.pawmot.euler.utils.Pair;
import com.pawmot.euler.utils.UnorderedPair;
import com.pawmot.euler.utils.memoization.Memoizer1;

public class Problem15 implements ProblemSolution {
    @Override
    public void printSolution() {
        byte n = 20;

        // This can be solved with one equation (think "Pascal's Triangle) but I thought that the recurrent memoized solution is quite elegant.
        long t1 = System.nanoTime();
        LatticePathsCounter counter = new LatticePathsCounter();
        Long count = counter.apply(UnorderedPair.from(n, n));
        long t2 = System.nanoTime();

        System.out.println(String.format("The number of paths in a %1$dx%1$d lattice is %2$d", n, count));
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }

    private static class LatticePathsCounter extends Memoizer1<Pair<Byte, Byte>, Long> {
        @Override
        protected Long compute(Pair<Byte, Byte> dims) {
            if(dims.get_1() == 0 || dims.get_2() == 0)
                return 1L;

            UnorderedPair<Byte> p1 = UnorderedPair.from((byte) (dims.get_1() - 1), dims.get_2());
            UnorderedPair<Byte> p2 = UnorderedPair.from(dims.get_1(), (byte) (dims.get_2() - 1));

            return this.apply(p1) + this.apply(p2);
        }
    }
}
