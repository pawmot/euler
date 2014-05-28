package com.pawmot.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final Map<Integer, ProblemSolution> problems;

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("Please select the problem number ('q' to quit):");
            String input = console.readLine();

            if(input.equalsIgnoreCase("q")) {
                break;
            }

            int problemNumber;
            try {
                problemNumber = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a positive integer or 'q'.");
                System.out.println();
                continue;
            }

            if(problemNumber < 1) {
                System.out.println("Enter a positive integer or 'q'.");
                System.out.println();
                continue;
            }

            if(!problems.containsKey(problemNumber)) {
                System.out.println("This problem I cannot solve... choose another one!");
                System.out.println();
                continue;
            }

            try {
                problems.get(problemNumber).printSolution();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }

    static {
        problems = new HashMap<>();

        problems.put(1, new Problem1());
        problems.put(2, new Problem2());
        problems.put(3, new Problem3());
        problems.put(4, new Problem4());
        problems.put(5, new Problem5());
        problems.put(6, new Problem6());
        problems.put(7, new Problem7());
        problems.put(8, new Problem8());
        problems.put(9, new Problem9());
        problems.put(10, new Problem10());
        problems.put(11, new Problem11());
        problems.put(12, new Problem12());
    }
}
