package com.pawmot.euler;

public class Problem9 implements ProblemSolution {
    @Override
    public void printSolution() {
        int n = 1000;

        for(int a = 1; a <= n-2; a++) {
            for(int b = a+1; b <= n-1-a; b++) {
                int c = n - a - b;

                if(a*a + b*b == c*c) {
                    System.out.println(String.format("The triplet that satisfies both a + b + c = 1000 and a^2 + b^2 = c^2 is: %1$d, %2$d, %3$d. The product is %4$d", a, b, c, a*b*c));
                }
            }
        }
    }
}
