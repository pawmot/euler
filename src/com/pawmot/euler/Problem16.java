package com.pawmot.euler;

import com.pawmot.euler.utils.lazySeqs.LazySeq;
import com.pawmot.euler.utils.lazySeqs.factories.CollectionsSeqs;

import java.util.ArrayList;

public class Problem16 implements ProblemSolution {
    @Override
    public void printSolution() {
        int n = 1000;

        long t1 = System.nanoTime();
        LazySeq<Integer> digits = digitsOfPowerOf2(n);

        Long sum = digits.foldLeft((a, b) -> a + b, 0L);
        long t2 = System.nanoTime();

        System.out.println(String.format("The sum of the digits of the number 2^%1$d is %2$d", n, sum));
        System.out.println(String.format("(it took %1$f ms)", (t2 - t1) / 1e6));
    }

    // TODO: express as a proper LazySeq instead of a wrapper on an array
    private LazySeq<Integer> digitsOfPowerOf2(int power) {
        assert power > -1;

        if(power == 0) return CollectionsSeqs.from(new Integer[] { 1 });

        LazySeq<Integer> lesser = digitsOfPowerOf2(power-1);

        int carry = 0;
        ArrayList<Integer> l = new ArrayList<>();
        LazySeq<Integer> t = lesser;
        while(!t.isEmpty()) {
            Integer lesserHead = t.head();

            int head = lesserHead * 2 + carry;
            carry = head/10;
            head -= carry * 10;

            l.add(head);
            t = t.tail();
        }

        while(carry != 0) {
            int head = carry;
            carry = head/10;
            head -= carry * 10;

            l.add(head);
        }

        return CollectionsSeqs.from(l);
    }
}
