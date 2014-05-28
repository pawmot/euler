package com.pawmot.euler;

public class Problem4 implements ProblemSolution {
    @Override
    public void printSolution() {
        int largest = Integer.MIN_VALUE;

        for(int i = 999; i > 99; i--) {
            if(i * 999 < largest) break;

            for(int j = 999; j > 99; j--) {
                int current = i * j;
                if(current < largest) break;

                if(isPalindrome(current)) {
                    largest = current;
                }
            }
        }

        System.out.println("Largest palindrome that is a multiplication of 2 3-digit numbers is " + largest);
    }

    private boolean isPalindrome(int current) {
        int digitCount = (int)Math.ceil(Math.log10(current));

        int mask = (int)Math.pow(10, digitCount-1);
        int reversed = 0;
        int temp = current;
        for(int i = 0; i < digitCount; i++) {
            reversed += (temp % 10) * mask;
            mask /= 10;
            temp /= 10;
        }

        return reversed == current;
    }
}
