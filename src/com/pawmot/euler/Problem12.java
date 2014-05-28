package com.pawmot.euler;

import java.util.ArrayList;
import java.util.List;

public class Problem12 implements ProblemSolution {
    @Override
    public void printSolution() {
        int n = 500;

        List<Long> divs = new ArrayList<>();
        long current = 0;
        long i = 1;
        while(divs.size() <= n) {
            current += i++;

            divs = new ArrayList<>();
            for(long div = 1; div*div <= current; div++) {
                if(current % div == 0) {
                    divs.add(div);
                    if(div*div != current) {
                        divs.add(current / div);
                    }
                }
            }
        }

        System.out.println("First triangle number with over 500 divisors is " + current);
    }
}
