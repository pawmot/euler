package com.pawmot.euler;

import java.util.LinkedList;
import java.util.Queue;

public class Problem8 implements ProblemSolution {
    @Override
    public void printSolution() {
        String number = "73167176531330624919225119674426574742355349194934\n" +
                "96983520312774506326239578318016984801869478851843\n" +
                "85861560789112949495459501737958331952853208805511\n" +
                "12540698747158523863050715693290963295227443043557\n" +
                "66896648950445244523161731856403098711121722383113\n" +
                "62229893423380308135336276614282806444486645238749\n" +
                "30358907296290491560440772390713810515859307960866\n" +
                "70172427121883998797908792274921901699720888093776\n" +
                "65727333001053367881220235421809751254540594752243\n" +
                "52584907711670556013604839586446706324415722155397\n" +
                "53697817977846174064955149290862569321978468622482\n" +
                "83972241375657056057490261407972968652414535100474\n" +
                "82166370484403199890008895243450658541227588666881\n" +
                "16427171479924442928230863465674813919123162824586\n" +
                "17866458359124566529476545682848912883142607690042\n" +
                "24219022671055626321111109370544217506941658960408\n" +
                "07198403850962455444362981230987879927244284909188\n" +
                "84580156166097919133875499200524063689912560717606\n" +
                "05886116467109405077541002256983155200055935729725\n" +
                "71636269561882670428252483600823257530420752963450";

        long max = 0;
        Queue<Integer> q = new LinkedList<>();

        long current = 1;
        for (int i = 0; i < number.length(); i++) {
            String c = number.substring(i, i+1);

            if(c.equals("\n")) {
                continue;
            }

            int digit = Integer.parseInt(c);

            if(digit == 0) {
                q.clear();
                current = 1;
                continue;
            }

            if(q.size() == 13) {
                int dropOut = q.poll();
                current /= dropOut;
            }

            q.offer(digit);
            current *= digit;

            if(q.size() == 13 && current > max) {
                max = current;
            }
        }

        System.out.println("The largest product of 13 consecutive digits in\n" + number + "\nis " + max);
    }
}
